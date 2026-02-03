package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DataMaps {
	private static final Map<String, PlayerData> PLAYER_DATA = new ConcurrentHashMap<>();
	private static final Map<String, PlayerInventory> PLAYER_INVENTORY = new ConcurrentHashMap<>();

	private DataMaps() {
	}

	public static PlayerData getPlayerData(String username, boolean createIfAbsent) {
		return PLAYER_DATA.computeIfAbsent(username, key -> {
			if (!createIfAbsent) {
				return null;
			}
			Cloud.getInstance().getCloudManager().log("[DataMaps] Creating new player data for " + key);
			return new PlayerData(key);
		});
	}

	public static PlayerInventory getPlayerInventory(String username, boolean createIfAbsent) {
		return PLAYER_INVENTORY.computeIfAbsent(username, key -> {
			if (!createIfAbsent) {
				return null;
			}
			Cloud.getInstance().getCloudManager().log("[DataMaps] Creating new player inventory for " + key);
			return initializeInventory(new PlayerInventory(key));
		});
	}

	private static PlayerInventory initializeInventory(PlayerInventory inventory) {
		for (CloudItem item : CloudItems.all()) {
			if (item.isDefault()) {
				inventory.addItemStack(new CloudItemStack(item), false);
			}
		}

		inventory.addItemStack(new CloudItemStack(CloudItems.COIN_ALPHA), true);
		inventory.addItemStack(new CloudItemStack(CloudItems.WC_ALPHA), true);
		return inventory;
	}
}
