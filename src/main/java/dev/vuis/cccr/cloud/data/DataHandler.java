package dev.vuis.cccr.cloud.data;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import lombok.Getter;

public final class DataHandler {
	@Getter
	private final CloudDatabase database;

	private final Map<String, UUID> playerUuids = new ConcurrentHashMap<>();
	private final Map<UUID, String> playerUsernames = new ConcurrentHashMap<>();
	private final Map<UUID, Set<UUID>> playerTradeUuids = new ConcurrentHashMap<>();

	private final LoadingCache<UUID, PlayerData> playerDataCache;
	private final LoadingCache<UUID, PlayerInventory> playerInventoryCache;
	private final Cache<UUID, Trade> tradeCache;

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
					String username = playerUsernames.getOrDefault(key, "Unknown");
					PlayerData data = database.loadPlayerData(key);
					if (data == null) {
						Cloud.instanceLog("[DataHandler] Creating new player data for %s", username);
						data = new PlayerData(key, username);
					}
					playerUsernames.put(key, username);
					return data;
				}
			});
		this.playerInventoryCache = CacheBuilder.newBuilder()
			.expireAfterAccess(5, TimeUnit.MINUTES)
			.removalListener(notification -> {
				try {
					Object value = notification.getValue();
					assert value != null;
					database.savePlayerInventory((PlayerInventory) value);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			})
			.build(new CacheLoader<>() {
				@Override
				public PlayerInventory load(UUID key) throws Exception {
					String username = playerUsernames.getOrDefault(key, "Unknown");
					PlayerInventory inventory = database.loadPlayerInventory(key, username);
					if (inventory == null) {
						Cloud.instanceLog("[DataHandler] Creating new player inventory for %s", username);
						inventory = initializeInventory(new PlayerInventory(key, username));
					}
					return inventory;
				}
			});
		this.tradeCache = CacheBuilder.newBuilder()
			.expireAfterAccess(5, TimeUnit.MINUTES)
			.removalListener(notification -> {
				try {
					Object key = notification.getKey();
					assert key != null;
					Object value = notification.getValue();
					assert value != null;
					database.saveTrade((UUID) key, (Trade) value);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			})
			.build();
	}

	public static DataHandler instance() {
		return Cloud.instance().getDataHandler();
	}

	public UUID getPlayerUuid(String username) {
		return playerUuids.computeIfAbsent(username, key -> {
			try {
				return database.loadPlayerUuid(key);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	public String getPlayerUsername(UUID uuid) {
		return playerUsernames.get(uuid);
	}

	public PlayerData getPlayerData(UUID uuid) {
		try {
			return playerDataCache.get(uuid);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
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
		try {
			playerInventoryCache.get(uuid).username.set(username);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}

		return data;
	}

	public PlayerInventory getPlayerInventory(UUID uuid) {
		try {
			return playerInventoryCache.get(uuid);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public PlayerInventory getPlayerInventory(String username) {
		UUID uuid = getPlayerUuid(username);
		return uuid != null ? getPlayerInventory(uuid) : null;
	}

	public Collection<PlayerInventory> getCachedPlayerInventories() {
		return Collections.unmodifiableCollection(playerInventoryCache.asMap().values());
	}

	private static PlayerInventory initializeInventory(PlayerInventory inventory) {
		inventory.addItemStacks(CloudItems.defaults().stream().map(CloudItemStack::new));
		inventory.addItemStack(new CloudItemStack(CloudItems.COIN_ALPHA), true);
		return inventory;
	}

	public Trade getTrade(UUID uuid) {
		Trade trade = tradeCache.getIfPresent(uuid);
		if (trade != null) {
			return trade;
		}

		try {
			trade = database.loadTrade(uuid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (trade != null) {
			tradeCache.put(uuid, trade);
		}
		return trade;
	}

	public Map<UUID, Trade> getPlayerTrades(UUID playerUuid) {
		Set<UUID> tradeUuids = playerTradeUuids.get(playerUuid);
		if (tradeUuids == null) {
			Map<UUID, Trade> trades;
			try {
				trades = database.loadPlayerTrades(playerUuid);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			tradeCache.putAll(trades);

			Set<UUID> newTradeUuids = ConcurrentHashMap.newKeySet();
			newTradeUuids.addAll(trades.keySet());
			playerTradeUuids.put(playerUuid, newTradeUuids);

			return trades;
		} else {
			Map<UUID, Trade> trades = new HashMap<>();
			for (UUID uuid : tradeUuids) {
				trades.put(uuid, getTrade(uuid));
			}
			return trades;
		}
	}

	public Map<UUID, Trade> getPlayerTrades(String username) {
		UUID uuid = getPlayerUuid(username);
		return uuid != null ? getPlayerTrades(uuid) : null;
	}

	public Set<Map.Entry<UUID, Trade>> getCachedTrades() {
		return Collections.unmodifiableSet(tradeCache.asMap().entrySet());
	}

	public void populateTrade(UUID uuid, Trade trade) {
		tradeCache.put(uuid, trade);

		Set<UUID> offererTrades = playerTradeUuids.get(trade.offererUuid);
		if (offererTrades != null) {
			offererTrades.add(uuid);
		}

		Set<UUID> returnerTrades = playerTradeUuids.get(trade.returnerUuid);
		if (returnerTrades != null) {
			returnerTrades.add(uuid);
		}
	}

	public void fullSaveCachedData() {
		for (PlayerData data : playerDataCache.asMap().values()) {
			try {
				database.savePlayerData(data, true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (PlayerInventory inventory : playerInventoryCache.asMap().values()) {
			try {
				database.savePlayerInventory(inventory);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Map.Entry<UUID, Trade> trade : tradeCache.asMap().entrySet()) {
			try {
				database.saveTrade(trade.getKey(), trade.getValue());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
