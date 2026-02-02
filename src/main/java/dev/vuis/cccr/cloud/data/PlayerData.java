package dev.vuis.cccr.cloud.data;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.util.Util;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class PlayerData {
	// player data
	public final AtomicInteger exp = new AtomicInteger(0);
	public final AtomicBoolean online = new AtomicBoolean(false);
	public final AtomicReference<String> mood = new AtomicReference<>("I haven't set a mood yet...");
	public final AtomicReference<String> status = new AtomicReference<>("Main Menu");
	public final AtomicReference<UUID> uuid = new AtomicReference<>(Util.NULL_UUID);

	// friends
	public final Set<String> friends = ConcurrentHashMap.newKeySet();
	public final Set<String> friendRequests = ConcurrentHashMap.newKeySet();

	public PlayerData(final String username) {
		new Thread(() -> retrievePlayerUuid(username), "Player UUID thread").start();
	}

	private void retrievePlayerUuid(final String username) {
		G_CloudManager cloudManager = Cloud.getInstance().getCloudManager();

		HttpRequest request = HttpRequest.newBuilder()
			.GET()
			.uri(URI.create("https://api.minetools.eu/uuid/" + username))
			.build();

		HttpResponse<String> response;
		try {
			response = Util.HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		if (response.statusCode() != 200) {
			cloudManager.log("Failed to retrieve UUID for player " + username);
			G_CloudTcpConnectionHandler connectionHandler = cloudManager.getNetwork().getClientConnectionHandler(CloudConstants.CHANNEL_PLAYER, username);
			if (connectionHandler != null) {
				connectionHandler.kickClientConnection("Failed to retrieve UUID");
			}
			return;
		}

		JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
		uuid.set(Util.parseUndashedUuid(json.get("id").getAsString()));

		cloudManager.log("Successfully retrieved UUID for player " + username);
	}

	public void putDataTag(FDSTagCompound tag) {
		tag.setInteger("exp", exp.get());
		tag.setBoolean("online", online.get());
		tag.setString("mood", mood.get());
		tag.setString("status", status.get());
		tag.setString("playerUUID", Util.getUndashedUuid(uuid.get()));
		tag.setBoolean("enableNotification_FriendRequest", true);
		tag.setBoolean("enableNotification_FriendPoke", true);
		tag.setBoolean("enableNotification_TradeRequest", true);
		tag.setBoolean("enableProfileViewing", true);
		tag.setBoolean("enableTradeRequests", true);
		tag.setBoolean("enableFriendRequests", true);
		tag.setString("clantag", "NONE");
		tag.setBoolean("twoFactorEnable", false);
		tag.setBoolean("isSupporter", false);
		tag.setBoolean("enableCape", false);
		tag.setBoolean("hasparty", false);
	}

	public void putFriendsTag(FDSTagCompound tag) {
		tag.setStringArrayList("friends", new ArrayList<>(friends));
		tag.setStringArrayList("pending", new ArrayList<>(friendRequests));
	}
}
