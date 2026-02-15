package dev.vuis.cccr.cloud.data;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;

public final class DataHandler {
	private final CloudDatabase database;

	private final Map<String, UUID> playerUuids = new ConcurrentHashMap<>();
	private final Map<UUID, String> playerUsernames = new ConcurrentHashMap<>();

	private final LoadingCache<UUID, PlayerData> playerDataCache;
	private final Map<UUID, PlayerInventory> playerInventory = new ConcurrentHashMap<>();

	public DataHandler(DataSource dataSource) {
		this.database = new CloudDatabase(dataSource);

		this.playerDataCache = CacheBuilder.newBuilder()
			.expireAfterAccess(5, TimeUnit.MINUTES)
			.removalListener(notification -> {
				try {
					Object value = notification.getValue();
					assert value != null;
					database.savePlayerData((PlayerData) value, true);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			})
			.build(new CacheLoader<>() {
				@Override
				public PlayerData load(UUID key) throws Exception {
					PlayerData data = database.loadPlayerData(key);
					if (data == null) {
						String username = playerUsernames.getOrDefault(key, "Unknown");
						Cloud.instanceLog("[DataHandler] Creating new player data for %s", username);
						data = new PlayerData(key, username);
					}
					return data;
				}
			});
	}

	public static DataHandler instance() {
		return Cloud.instance().getDataHandler();
	}

	public UUID getPlayerUuid(String username) {
		return playerUuids.get(username);
	}

	public String getPlayerUsername(UUID uuid) {
		return playerUsernames.get(uuid);
	}

	public PlayerData getPlayerData(UUID uuid) {
		return playerDataCache.getIfPresent(uuid);
	}

	public PlayerData getPlayerData(String username) {
		UUID uuid = getPlayerUuid(username);
		return uuid != null ? getPlayerData(uuid) : null;
	}

	public Collection<PlayerData> getCachedPlayerData() {
		return Collections.unmodifiableCollection(playerDataCache.asMap().values());
	}

	public PlayerData populatePlayerData(String username, UUID uuid) {
		playerUuids.put(username, uuid);
		playerUsernames.put(uuid, username);

		PlayerData data;
		try {
			data = playerDataCache.get(uuid);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}

		data.dirty.mark();
		data.username.set(username);
		populatePlayerInventory(username, uuid);

		return data;
	}

	public void saveCachedPlayerData() {
		for (PlayerData data : playerDataCache.asMap().values()) {
			try {
				database.savePlayerData(data, true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public PlayerInventory getPlayerInventory(UUID uuid) {
		return playerInventory.get(uuid);
	}

	public PlayerInventory getPlayerInventory(String username) {
		UUID uuid = getPlayerUuid(username);
		return uuid != null ? getPlayerInventory(uuid) : null;
	}

	public PlayerInventory populatePlayerInventory(String username, UUID uuid) {
		PlayerInventory inventory = playerInventory.computeIfAbsent(uuid, _ -> {
			Cloud.instanceLog("[DataHandler] Creating new player inventory for %s", username);
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
