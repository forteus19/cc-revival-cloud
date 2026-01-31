package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.main.Cloud;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataHolder implements IDataHolder {
	private final Map<String, IPlayerData> playerDataMap = new ConcurrentHashMap<>();

	@Override
	public IPlayerData getPlayerData(String username, boolean createIfAbsent) {
		if (createIfAbsent && !playerDataMap.containsKey(username)) {
			Cloud.getInstance().getCloudManager().log("[InMemoryDataHolder] Creating new player data for " + username);

			IPlayerData playerData = new InMemoryPlayerData();
			playerDataMap.put(username, playerData);
			return playerData;
		}

		return playerDataMap.get(username);
	}
}
