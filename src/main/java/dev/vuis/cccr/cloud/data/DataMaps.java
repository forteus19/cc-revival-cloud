package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.Cloud;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DataMaps {
	private static final Map<String, PlayerData> playerDataMap = new ConcurrentHashMap<>();

	private DataMaps() {
	}

	public static PlayerData getPlayerData(String username, boolean createIfAbsent) {
		if (createIfAbsent && !playerDataMap.containsKey(username)) {
			Cloud.getInstance().getCloudManager().log("[DataMaps] Creating new player data for " + username);

			PlayerData playerData = new PlayerData(username);
			playerDataMap.put(username, playerData);
			return playerData;
		}

		return playerDataMap.get(username);
	}
}
