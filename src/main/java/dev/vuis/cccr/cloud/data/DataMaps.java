package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class DataMaps {
	private static final Map<String, UUID> PLAYER_UUIDS = new ConcurrentHashMap<>();
	private static final Map<UUID, String> PLAYER_USERNAMES = new ConcurrentHashMap<>();

	private static final Map<UUID, PlayerData> PLAYER_DATA = new ConcurrentHashMap<>();
	private static final Map<UUID, PlayerInventory> PLAYER_INVENTORY = new ConcurrentHashMap<>();

	private DataMaps() {
	}

	public static UUID getPlayerUuid(String username) {
		return PLAYER_UUIDS.get(username);
	}

	public static String getPlayerUsername(UUID uuid) {
		return PLAYER_USERNAMES.get(uuid);
	}

	public static PlayerData getPlayerData(UUID uuid) {
		return PLAYER_DATA.get(uuid);
	}

	public static PlayerData getPlayerData(String username) {
		UUID uuid = getPlayerUuid(username);
		return uuid != null ? getPlayerData(uuid) : null;
	}

	public static Collection<PlayerData> getCachedPlayerData() {
		return Collections.unmodifiableCollection(PLAYER_DATA.values());
	}

	public static PlayerData populatePlayerData(String username, UUID uuid) {
		PlayerData data = PLAYER_DATA.computeIfAbsent(uuid, k -> {
			Cloud.instanceLog("[DataMaps] Creating new player data for %s", username);
			return new PlayerData(k, username);
		});

		PLAYER_UUIDS.put(username, uuid);
		PLAYER_USERNAMES.put(uuid, username);
		data.username.set(username);
		populatePlayerInventory(username, uuid);

		return data;
	}

	public static PlayerInventory getPlayerInventory(UUID uuid) {
		return PLAYER_INVENTORY.get(uuid);
	}

	public static PlayerInventory getPlayerInventory(String username) {
		UUID uuid = getPlayerUuid(username);
		return uuid != null ? getPlayerInventory(uuid) : null;
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
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_ALPHA1), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_ALPHA), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_ALPHA2), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_ALPHA2), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_ALPHA3), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_ALPHA3), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_HAT), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_HAT), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_HALLOWEEN), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_HALLOWEEN), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_EXO), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_EXO), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_PREALPHA), false);
			inventory.addItemStack(new CloudItemStack(CloudItems.CASE_KEY_PREALPHA), false);
		}

		return inventory;
	}
}
