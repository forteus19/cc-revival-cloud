package dev.vuis.cccr.cloud;

import dev.vuis.cccr.cloud.util.Util;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CloudAuth {
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

		int statusCode = httpClient.send(request, HttpResponse.BodyHandlers.discarding()).statusCode();
		if (!Util.isHttpSuccess(statusCode)) {
			Cloud.instanceLog("Authentication for " + username + " failed with status code " + statusCode);
			onAuthenticationFailure(username);
			return;
		}

		onAuthenticationSuccess(username);
	}

	private static URI getSessionJoinedUri(String username, String serverId) {
		return URI.create(
			"https://sessionserver.mojang.com/session/minecraft/hasJoined?username=" + urlEncode(username) + "&serverId=" + urlEncode(serverId)
		);
	}

	private static String urlEncode(String s) {
		return URLEncoder.encode(s, StandardCharsets.UTF_8);
	}

	private void onAuthenticationSuccess(String username) {
		authenticated.add(username);
		cloud.onConnectionAuthenticationSuccess(cloud.getPlayerConnection(username));
	}

	private void onAuthenticationFailure(String username) {
		cloud.onConnectionAuthenticationFailure(cloud.getPlayerConnection(username));
	}

	public boolean isAuthenticated(String username) {
		return authenticated.contains(username);
	}

	public void onClientDisconnected(String username) {
		authenticated.remove(username);
	}
}
