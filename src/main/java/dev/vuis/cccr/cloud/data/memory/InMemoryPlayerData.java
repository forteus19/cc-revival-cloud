package dev.vuis.cccr.cloud.data.memory;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.IPlayerData;
import dev.vuis.cccr.cloud.util.Util;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryPlayerData implements IPlayerData {
	// player data
	private int exp = 0;
	private boolean online = false;
	private String mood = "I haven't set a mood yet...";
	private String status = "Main Menu";
	private final AtomicReference<UUID> uuid = new AtomicReference<>(Util.NULL_UUID);

	// friends
	private final ArrayList<String> friends = new ArrayList<>();
	private final ArrayList<String> friendRequests = new ArrayList<>();

	public InMemoryPlayerData(final String username) {
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

	@Override
	public int getExp() {
		return exp;
	}

	@Override
	public void setExp(int exp) {
		this.exp = exp;
	}

	@Override
	public boolean isOnline() {
		return online;
	}

	@Override
	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public String getMood() {
		return mood;
	}

	@Override
	public void setMood(String mood) {
		this.mood = mood;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public UUID getUuid() {
		return uuid.get();
	}

	@Override
	public void setUuid(UUID uuid) {
		this.uuid.set(uuid);
	}

	@Override
	public ArrayList<String> getFriends() {
		return friends;
	}

	@Override
	public ArrayList<String> getFriendRequests() {
		return friendRequests;
	}
}
