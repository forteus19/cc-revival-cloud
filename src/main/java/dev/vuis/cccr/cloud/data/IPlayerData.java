package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.ArrayList;
import java.util.UUID;

public interface IPlayerData {
	int getExp();

	void setExp(int exp);

	boolean isOnline();

	void setOnline(boolean online);

	String getMood();

	void setMood(String mood);

	String getStatus();

	void setStatus(String status);

	UUID getUuid();

	void setUuid(UUID uuid);

	ArrayList<String> getFriends();

	ArrayList<String> getFriendRequests();

	default void putDataTag(FDSTagCompound tag) {
		tag.setInteger("exp", getExp());
		tag.setBoolean("online", isOnline());
		tag.setString("mood", getMood());
		tag.setString("status", getStatus());
		tag.setString("playerUUID", getUuid().toString());
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

	default void putFriendsTag(FDSTagCompound tag) {
		tag.setStringArrayList("friends", getFriends());
		tag.setStringArrayList("pending", getFriendRequests());
	}
}
