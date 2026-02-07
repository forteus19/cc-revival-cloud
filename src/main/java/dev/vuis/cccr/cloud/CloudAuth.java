package dev.vuis.cccr.cloud;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dev.vuis.cccr.cloud.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CloudAuth {
	private static final Gson GSON = new Gson();

	private final HttpClient httpClient = HttpClient.newHttpClient();
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private final Set<String> authenticated = ConcurrentHashMap.newKeySet();

	private final Cloud cloud;

	public void submitAuthenticationJob(String username, String serverId) {
		executor.submit(() -> {
			try {
				authenticate(username, serverId);
			} catch (InterruptedException | IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void authenticate(String username, String serverId) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
			.GET()
			.uri(getSessionJoinedUri(username, serverId))
			.build();

		HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
		int statusCode = response.statusCode();

		if (!Util.isHttpSuccess(statusCode)) {
			onAuthenticationFailure(username, statusCode);
			return;
		}

		JsonObject json;
		try {
			json = GSON.fromJson(new InputStreamReader(response.body()), JsonObject.class);
		} catch (JsonSyntaxException e) {
			onAuthenticationFailure(username, statusCode);
			return;
		}
		if (json == null) {
			onAuthenticationFailure(username, statusCode);
			return;
		}

		onAuthenticationSuccess(username, Util.parseUndashedUuid(json.get("id").getAsString()));
	}

	private static URI getSessionJoinedUri(String username, String serverId) {
		return URI.create(
			"https://sessionserver.mojang.com/session/minecraft/hasJoined?username=" + urlEncode(username) + "&serverId=" + urlEncode(serverId)
		);
	}

	private static String urlEncode(String s) {
		return URLEncoder.encode(s, StandardCharsets.UTF_8);
	}

	private void onAuthenticationSuccess(String username, UUID resultUuid) {
		authenticated.add(username);
		cloud.onConnectionAuthenticationSuccess(cloud.getPlayerConnection(username), resultUuid);
	}

	private void onAuthenticationFailure(String username, int statusCode) {
		Cloud.instanceLog("Authentication for %s failed with status code %d", username, statusCode);
		cloud.onConnectionAuthenticationFailure(cloud.getPlayerConnection(username));
	}

	public boolean isAuthenticated(String username) {
		return authenticated.contains(username);
	}

	public void onClientDisconnected(String username) {
		authenticated.remove(username);
	}
}
