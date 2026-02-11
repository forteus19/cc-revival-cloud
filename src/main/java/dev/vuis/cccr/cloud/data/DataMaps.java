package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class DataMaps {
	private static final Map<String, UUID> PLAYER_UUIDS = new ConcurrentHashMap<>();

	private static final Map<UUID, PlayerData> PLAYER_DATA = new ConcurrentHashMap<>();
	private static final Map<UUID, PlayerInventory> PLAYER_INVENTORY = new ConcurrentHashMap<>();

	private DataMaps() {
	}

	public static PlayerData getPlayerData(String username) {
		UUID uuid = PLAYER_UUIDS.get(username);
		return uuid != null ? PLAYER_DATA.get(uuid) : null;
	}

	public static PlayerData populatePlayerData(String username, UUID uuid) {
		PlayerData data = PLAYER_DATA.computeIfAbsent(uuid, k -> {
			Cloud.instanceLog("[DataMaps] Creating new player data for %s", username);
			return new PlayerData(k, username);
		});

		PLAYER_UUIDS.put(username, uuid);
		data.username.set(username);
		populatePlayerInventory(username, uuid);

		return data;
	}

	public static PlayerInventory getPlayerInventory(String username) {
		UUID uuid = PLAYER_UUIDS.get(username);
		return uuid != null ? PLAYER_INVENTORY.get(uuid) : null;
	}

	public static PlayerInventory populatePlayerInventory(String username, UUID uuid) {
		PlayerInventory inventory = PLAYER_INVENTORY.computeIfAbsent(uuid, _ -> {
			Cloud.instanceLog("[DataMaps] Creating new player inventory for %s", username);
			return initializeInventory(new PlayerInventory(username));
		});
		inventory.username.set(username);
		return inventory;
	}

	private static PlayerInventory initializeInventory(PlayerInventory inventory) {
		inventory.addItemStacks(CloudItems.defaults().stream().map(CloudItemStack::new));

		inventory.addItemStack(new CloudItemStack(CloudItems.COIN_ALPHA), true);

		for (int i = 0; i < 10; i++) {
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
		}

		return inventory;
	}
}
