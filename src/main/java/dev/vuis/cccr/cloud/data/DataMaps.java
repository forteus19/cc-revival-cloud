package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.Cloud;
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
			Cloud.instanceLog("[DataMaps] Creating new player data for %s", key);
			return new PlayerData(key);
		});
	}

	public static PlayerInventory getPlayerInventory(String username, boolean createIfAbsent) {
		return PLAYER_INVENTORY.computeIfAbsent(username, key -> {
			if (!createIfAbsent) {
				return null;
			}
			Cloud.instanceLog("[DataMaps] Creating new player inventory for %s", key);
			return initializeInventory(new PlayerInventory(key));
		});
	}

	private static PlayerInventory initializeInventory(PlayerInventory inventory) {
		inventory.addItemStacks(CloudItems.defaults().stream().map(CloudItemStack::new));

		inventory.addItemStack(new CloudItemStack(CloudItems.COIN_ALPHA), true);

		inventory.addItemStack(new CloudItemStack(CloudItems.WC_ALPHA), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_ALPHA), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.WC_ALPHA2), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_ALPHA2), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.WC_ALPHA3), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_ALPHA3), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.HC_ALPHA1), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_HAT), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.WC_HALLOWEEN1), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_HALLOWEEN1), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.WC_EXO), false);
		inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_EXO), false);

		return inventory;
	}
}
