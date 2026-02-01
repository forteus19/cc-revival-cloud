package dev.vuis.cccr.cloud.data.memory;

import dev.vuis.cccr.cloud.data.IPlayerData;
import java.util.ArrayList;
import java.util.UUID;

public class InMemoryPlayerData implements IPlayerData {
	// player data
	private int exp = 0;
	private boolean online = false;
	private String mood = "super awesome mood";
	private String status = "Main Menu";
	private UUID uuid = UUID.randomUUID(); // FIXME

	// friends
	private final ArrayList<String> friends = new ArrayList<>();
	private final ArrayList<String> friendRequests = new ArrayList<>();

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
		return uuid;
	}

	@Override
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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
