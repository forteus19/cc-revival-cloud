package dev.vuis.cccr.cloud.data;

import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.vuis.cccr.cloud.Cloud;
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
	public final AtomicReference<String> username;
	public final AtomicReference<UUID> uuid = new AtomicReference<>(Util.NULL_UUID);
	public final AtomicInteger trophies = new AtomicInteger(0);
	public final AtomicInteger kills = new AtomicInteger(0);
	public final AtomicInteger deaths = new AtomicInteger(0);
	public final AtomicInteger games = new AtomicInteger(0);
	public final AtomicInteger firstBloods = new AtomicInteger(0);
	public final AtomicInteger matchKarma = new AtomicInteger(0);
	public final AtomicInteger exp = new AtomicInteger(0);
	public final AtomicBoolean online = new AtomicBoolean(false);
	public final AtomicInteger emeralds = new AtomicInteger(0);
	public final AtomicReference<String> mood = new AtomicReference<>("");
	public final AtomicReference<String> status = new AtomicReference<>("Main Menu");

	// friends
	public final Set<String> friends = ConcurrentHashMap.newKeySet();
	public final Set<String> friendRequests = ConcurrentHashMap.newKeySet();

	public PlayerData(final String username) {
		this.username = new AtomicReference<>(username);
//		new Thread(() -> retrievePlayerUuid(username), "Player UUID thread").start();
	}

	private void retrievePlayerUuid(final String username) {
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
			Cloud.instanceLog("Failed to retrieve UUID for player %s", username);
			G_CloudTcpConnectionHandler connectionHandler = Cloud.getInstance().getPlayerConnection(username);
			if (connectionHandler != null) {
				connectionHandler.kickClientConnection("Failed to retrieve UUID");
			}
			return;
		}

		JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
		uuid.set(Util.parseUndashedUuid(json.get("id").getAsString()));

		Cloud.instanceLog("Successfully retrieved UUID for player %s", username);
	}

	public void writeDataTag(FDSTagCompound tag) {
		tag.setInteger("trophies", trophies.get());
		tag.setInteger("kills", kills.get());
		tag.setInteger("deaths", deaths.get());
		tag.setInteger("games", games.get());
		tag.setInteger("statfb", firstBloods.get());
		tag.setInteger("matchKarma", matchKarma.get());
		tag.setInteger("exp", exp.get());
		tag.setBoolean("online", online.get());
		tag.setInteger("emeralds", emeralds.get());
		tag.setString("mood", mood.get());
		tag.setString("status", status.get());
		tag.setString("playerUUID", Util.getUndashedUuid(uuid.get()));

		tag.setString("discordUserId", "");
		tag.setBoolean("enableNotification_FriendRequest", true);
		tag.setBoolean("enableNotification_FriendPoke", true);
		tag.setBoolean("enableNotification_TradeRequest", true);
		tag.setBoolean("enableProfileViewing", true);
		tag.setBoolean("enableTradeRequests", true);
		tag.setBoolean("enableFriendRequests", true);
		tag.setBoolean("twoFactorEnable", false);
		tag.setBoolean("isSupporter", false);
		tag.setBoolean("enableCape", false);
		tag.setBoolean("hasparty", false);
	}

	public void writeFriendsTag(FDSTagCompound tag) {
		tag.setStringArrayList("friends", new ArrayList<>(friends));
		tag.setStringArrayList("pending", new ArrayList<>(friendRequests));
	}
}
