package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.util.Util;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class PlayerData {
	public final UUID uuid;
	public final AtomicReference<String> username;

	// player data
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

	PlayerData(UUID uuid, String username) {
		this.uuid = uuid;
		this.username = new AtomicReference<>(username);
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
		tag.setString("playerUUID", Util.getUndashedUuid(uuid));

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
