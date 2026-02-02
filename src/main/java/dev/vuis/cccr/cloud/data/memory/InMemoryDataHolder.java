package dev.vuis.cccr.cloud.data.memory;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.data.IDataHolder;
import dev.vuis.cccr.cloud.data.IPlayerData;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataHolder implements IDataHolder {
	private final Map<String, IPlayerData> playerDataMap = new ConcurrentHashMap<>();

	@Override
	public IPlayerData getPlayerData(String username, boolean createIfAbsent) {
		if (createIfAbsent && !playerDataMap.containsKey(username)) {
			Cloud.getInstance().getCloudManager().log("[InMemoryDataHolder] Creating new player data for " + username);

			IPlayerData playerData = new InMemoryPlayerData(username);
			playerDataMap.put(username, playerData);
			return playerData;
		}

		return playerDataMap.get(username);
	}
}
