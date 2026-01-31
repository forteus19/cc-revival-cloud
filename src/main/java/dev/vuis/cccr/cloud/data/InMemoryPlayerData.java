package dev.vuis.cccr.cloud.data;

import java.util.UUID;

public class InMemoryPlayerData implements IPlayerData {
	private int exp = 0;
	private boolean online = false;
	private String mood = "super awesome mood";
	private String status = "Main Menu";
	private UUID uuid = UUID.randomUUID(); // FIXME

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
}
