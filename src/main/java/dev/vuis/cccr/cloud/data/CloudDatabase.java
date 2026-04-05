package dev.vuis.cccr.cloud.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.util.ConnectionConsumer;
import dev.vuis.cccr.cloud.util.ConnectionFunction;
import dev.vuis.cccr.cloud.util.Pair;
import dev.vuis.cccr.cloud.util.Util;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CloudDatabase {
	private final DataSource dataSource;

	public void acceptConnection(ConnectionConsumer consumer) {
		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);
			try {
				consumer.accept(connection);
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public <R> R applyConnection(ConnectionFunction<R> function) {
		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);
			try {
				R result = function.apply(connection);
				connection.commit();
				return result;
			} catch (SQLException e) {
				connection.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void savePlayerData(Connection connection, PlayerData data, boolean ignoreDirty) throws SQLException {
		if (ignoreDirty || data.dirty.isDirty()) {
			try (PreparedStatement ps = connection.prepareStatement(
				"insert into players (uuid, username, trophies, kills, deaths, games, first_bloods, match_karma, minutes_played, exp, emeralds, mood, achievements, group_tag, prestige, comp_exp, score, clan_tag) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
					"on conflict (uuid) do update set " +
					"username = excluded.username, trophies = excluded.trophies, kills = excluded.kills, deaths = excluded.deaths, " +
					"games = excluded.games, first_bloods = excluded.first_bloods, match_karma = excluded.match_karma, minutes_played = excluded.minutes_played, " +
					"exp = excluded.exp, emeralds = excluded.emeralds, mood = excluded.mood, achievements = excluded.achievements, " +
					"group_tag = excluded.group_tag, prestige = excluded.prestige, comp_exp = excluded.comp_exp, score = excluded.score, " +
					"clan_tag = excluded.clan_tag"
			)) {
				ps.setObject(1, data.uuid);
				ps.setString(2, data.username.get());
				ps.setInt(3, data.trophies.get());
				ps.setInt(4, data.kills.get());
				ps.setInt(5, data.deaths.get());
				ps.setInt(6, data.games.get());
				ps.setInt(7, data.firstBloods.get());
				ps.setInt(8, data.matchKarma.get());
				ps.setInt(9, data.minutesPlayed.get());
				ps.setInt(10, data.exp.get());
				ps.setInt(11, data.emeralds.get());
				ps.setString(12, data.mood.get());
				ps.setArray(13, connection.createArrayOf("integer", data.achievements.toArray()));
				ps.setString(14, Util.ifNonNull(data.group.get(), PlayerGroup::tag));
				ps.setInt(15, data.prestige.get());
				ps.setInt(16, data.compExp.get());
				ps.setInt(17, data.score.get());
				ps.setString(18, data.clanTag.get());

				ps.executeUpdate();
				data.dirty.clear();
			}
		}

		if (ignoreDirty || data.punishmentsDirty.isDirty()) {
			try (PreparedStatement ps = connection.prepareStatement(
				"delete from punishment_records where player_uuid = ?"
			)) {
				ps.setObject(1, data.uuid);

				ps.executeUpdate();
			}

			if (!data.punishments.isEmpty()) {
				try (PreparedStatement ps = connection.prepareStatement(
					"insert into punishment_records (uuid, player_uuid, issuer, type_ordinal, reason, start_time, end_time) values (?, ?, ?, ?, ?, ?, ?)"
				)) {
					for (Punishment punishment : data.punishments) {
						ps.setObject(1, punishment.uuid());
						ps.setObject(2, data.uuid);
						ps.setString(3, punishment.issuer());
						ps.setInt(4, punishment.type().ordinal());
						ps.setString(5, punishment.reason());
						ps.setTimestamp(6, Timestamp.valueOf(punishment.start()));
						ps.setTimestamp(7, Timestamp.valueOf(punishment.end()));
						ps.addBatch();
					}

					ps.executeBatch();
					data.punishmentsDirty.clear();
				}
			}
		}

		if (ignoreDirty || data.friendsDirty.isDirty()) {
			try (PreparedStatement ps = connection.prepareStatement(
				"delete from player_friends where player_uuid = ?"
			)) {
				ps.setObject(1, data.uuid);

				ps.executeUpdate();
			}

			if (!data.friends.isEmpty()) {
				try (PreparedStatement ps = connection.prepareStatement(
					"insert into player_friends (player_uuid, friend_uuid) values (?, ?)"
				)) {
					for (UUID friend : data.friends) {
						ps.setObject(1, data.uuid);
						ps.setObject(2, friend);
						ps.addBatch();
					}

					ps.executeBatch();
					data.friendsDirty.clear();
				}
			}
		}

		if (ignoreDirty || data.friendRequestsDirty.isDirty()) {
			try (PreparedStatement ps = connection.prepareStatement(
				"delete from player_friend_requests where player_uuid = ?"
			)) {
				ps.setObject(1, data.uuid);

				ps.executeUpdate();
			}

			if (!data.friendRequests.isEmpty()) {
				try (PreparedStatement ps = connection.prepareStatement(
					"insert into player_friend_requests (player_uuid, requester_uuid) values (?, ?)"
				)) {
					for (UUID friend : data.friendRequests) {
						ps.setObject(1, data.uuid);
						ps.setObject(2, friend);
						ps.addBatch();
					}

					ps.executeBatch();
					data.friendRequestsDirty.clear();
				}
			}
		}
	}

	public PlayerData loadPlayerData(Connection connection, UUID uuid) throws SQLException {
		PlayerData data;

		try (PreparedStatement ps = connection.prepareStatement(
			"select username, trophies, kills, deaths, games, first_bloods, match_karma, minutes_played, exp, emeralds, mood, achievements, group_tag, prestige, comp_exp, score, clan_tag from players where uuid = ?"
		)) {
			ps.setObject(1, uuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}

			data = new PlayerData(uuid, rs.getString("username"));
			data.trophies.set(rs.getInt("trophies"));
			data.kills.set(rs.getInt("kills"));
			data.deaths.set(rs.getInt("deaths"));
			data.games.set(rs.getInt("games"));
			data.firstBloods.set(rs.getInt("first_bloods"));
			data.matchKarma.set(rs.getInt("match_karma"));
			data.minutesPlayed.set(rs.getInt("minutes_played"));
			data.exp.set(rs.getInt("exp"));
			data.emeralds.set(rs.getInt("emeralds"));
			data.mood.set(rs.getString("mood"));
			data.achievements.addAll(Arrays.asList((Integer[]) rs.getArray("achievements").getArray()));
			String groupTag = rs.getString("group_tag");
			if (groupTag != null) {
				data.group.set(PlayerGroups.getByTag(groupTag));
			}
			data.prestige.set(rs.getInt("prestige"));
			data.compExp.set(rs.getInt("comp_exp"));
			data.score.set(rs.getInt("score"));
			data.clanTag.set(rs.getString("clan_tag"));
		}

		try (PreparedStatement ps = connection.prepareStatement(
			"select uuid, issuer, type_ordinal, reason, start_time, end_time from punishment_records where player_uuid = ?"
		)) {
			ps.setObject(1, uuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();

			List<Punishment> punishments = new ArrayList<>();
			while (rs.next()) {
				punishments.add(new Punishment(
					rs.getObject("uuid", UUID.class),
					rs.getString("issuer"),
					PunishmentType.values()[rs.getInt("type_ordinal")],
					rs.getString("reason"),
					rs.getTimestamp("start_time").toLocalDateTime(),
					rs.getTimestamp("end_time").toLocalDateTime()
				));
			}
			data.punishments.addAll(punishments);
		}

		try (PreparedStatement ps = connection.prepareStatement(
			"select friend_uuid from player_friends where player_uuid = ?"
		)) {
			ps.setObject(1, uuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				data.friends.add(rs.getObject("friend_uuid", UUID.class));
			}
		}

		try (PreparedStatement ps = connection.prepareStatement(
			"select requester_uuid from player_friend_requests where player_uuid = ?"
		)) {
			ps.setObject(1, uuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				data.friendRequests.add(rs.getObject("requester_uuid", UUID.class));
			}
		}

		return data;
	}

	public UUID loadPlayerUuid(Connection connection, String username) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
			"select uuid from players where username = ?"
		)) {
			ps.setString(1, username);

			@Cleanup
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}

			return rs.getObject("uuid", UUID.class);
		}
	}

	public List<Pair<String, Integer>> loadPlayerScores(Connection connection, int limit) throws SQLException {
		ImmutableList.Builder<Pair<String, Integer>> result = ImmutableList.builder();

		try (Statement st = connection.createStatement()) {
			@Cleanup
			ResultSet rs = st.executeQuery(
				"select username, score from players where score <> 0 order by score desc limit " + limit
			);

			while (rs.next()) {
				result.add(new Pair<>(rs.getString("username"), rs.getInt("score")));
			}
		}

		return result.build();
	}

	public void resetPlayerScores(Connection connection) throws SQLException {
		try (Statement st = connection.createStatement()) {
			st.executeUpdate("update players set score = 0 where score <> 0");
		}
	}

	public void savePlayerInventory(Connection connection, PlayerInventory inventory) throws SQLException {
		UUID ownerUuid = inventory.getOwnerUuid();

		try (PreparedStatement ps = connection.prepareStatement(
			"delete from player_inventories where owner_uuid = ?"
		)) {
			ps.setObject(1, ownerUuid);

			ps.executeUpdate();
		}

		try (PreparedStatement ps = connection.prepareStatement(
			"insert into player_inventories (uuid, owner_uuid, item_id, nametag, equipped) " +
				"values (?, ?, ?, ?, ?) " +
				"on conflict (uuid) do update set " +
				"owner_uuid = excluded.owner_uuid, item_id = excluded.item_id, nametag = excluded.nametag, equipped = excluded.equipped"
		)) {
			for (CloudItemStack stack : inventory.getItemStacks()) {
				ps.setObject(1, stack.uuid);
				ps.setObject(2, ownerUuid);
				ps.setInt(3, stack.item.getId());
				ps.setString(4, stack.nametag);
				ps.setBoolean(5, inventory.isDefaultStack(stack.uuid));

				ps.addBatch();
			}

			ps.executeBatch();
		}

		try (PreparedStatement ps = connection.prepareStatement(
			"insert into player_case_stats (player_uuid, case_item_id, result_item_id, count) " +
				"values (?, ?, ?, ?) " +
				"on conflict (player_uuid, case_item_id, result_item_id) do update set " +
				"count = excluded.count"
		)) {
			for (Map.Entry<CloudItemCase, Multiset<CloudItem>> caseEntry : inventory.caseStats.entrySet()) {
				int caseItemId = caseEntry.getKey().getId();

				for (Multiset.Entry<CloudItem> resultItem : caseEntry.getValue().entrySet()) {
					ps.setObject(1, ownerUuid);
					ps.setInt(2, caseItemId);
					ps.setInt(3, resultItem.getElement().getId());
					ps.setInt(4, resultItem.getCount());

					ps.addBatch();
				}
			}

			ps.executeBatch();
		}
	}

	private static CloudItemStack processItemStackQuery(UUID uuid, int itemId, String nametag) {
		CloudItem item = CloudItems.getById(itemId);
		if (item == null) {
			return null;
		}
		CloudItemStack stack = new CloudItemStack(uuid, item);
		stack.nametag = nametag;
		return stack;
	}

	public PlayerInventory loadPlayerInventory(Connection connection, UUID ownerUuid) throws SQLException {
		PlayerInventory inventory = null;

		List<Pair<CloudItemStack, Boolean>> loadedStacks = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(
			"select uuid, item_id, nametag, equipped from player_inventories where owner_uuid = ?"
		)) {
			ps.setObject(1, ownerUuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (inventory == null) {
					inventory = new PlayerInventory(ownerUuid);
				}

				CloudItemStack stack = processItemStackQuery(
					rs.getObject("uuid", UUID.class),
					rs.getInt("item_id"),
					rs.getString("nametag")
				);
				if (stack == null) {
					continue;
				}

				loadedStacks.add(new Pair<>(stack, rs.getBoolean("equipped")));
			}
		}

		if (inventory == null) {
			return null;
		}

		inventory.addItemStacks(loadedStacks.stream().map(Pair::left));
		for (Pair<CloudItemStack, Boolean> loadedStack : loadedStacks) {
			if (loadedStack.right()) {
				inventory.setDefaultStack(loadedStack.left().uuid);
			}
		}

		try (PreparedStatement ps = connection.prepareStatement(
			"select case_item_id, result_item_id, count from player_case_stats where player_uuid = ?"
		)) {
			ps.setObject(1, ownerUuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CloudItem baseItem = CloudItems.getById(rs.getInt("case_item_id"));
				if (!(baseItem instanceof CloudItemCase caseItem)) {
					continue;
				}
				CloudItem resultItem = CloudItems.getById(rs.getInt("result_item_id"));
				if (resultItem == null) {
					continue;
				}
				inventory.getCaseResultSet(caseItem).setCount(resultItem, rs.getInt("count"));
			}
		}

		return inventory;
	}

	public void saveTrade(Connection connection, UUID uuid, Trade trade) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
			"insert into trades (uuid, offerer_uuid, returner_uuid, start_time, end_time, result_ordinal, offer_stacks, return_stacks) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?) " +
				"on conflict (uuid) do update set " +
				"offerer_uuid = excluded.offerer_uuid, returner_uuid = excluded.returner_uuid, start_time = excluded.start_time, end_time = excluded.end_time, " +
				"result_ordinal = excluded.result_ordinal, offer_stacks = excluded.offer_stacks, return_stacks = excluded.return_stacks"
		)) {
			ps.setObject(1, uuid);
			ps.setObject(2, trade.offererUuid);
			ps.setObject(3, trade.returnerUuid);
			ps.setTimestamp(4, Timestamp.valueOf(trade.start));
			ps.setTimestamp(5, Util.ifNonNull(trade.end, Timestamp::valueOf));
			ps.setInt(6, trade.result.ordinal());
			ps.setArray(7, connection.createArrayOf("uuid", trade.offerStacks.stream().map(CloudItemStack::getUuid).toArray()));
			ps.setArray(8, connection.createArrayOf("uuid", trade.returnStacks.stream().map(CloudItemStack::getUuid).toArray()));

			ps.executeUpdate();
			trade.dirty.clear();
		}
	}

	private static List<CloudItemStack> loadItemStacks(Connection connection, Array uuids) throws SQLException {
		List<CloudItemStack> result = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(
			"select uuid, item_id, nametag from player_inventories where uuid = any (?::uuid[])"
		)) {
			ps.setArray(1, uuids);

			@Cleanup
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CloudItemStack stack = processItemStackQuery(
					rs.getObject("uuid", UUID.class),
					rs.getInt("item_id"),
					rs.getString("nametag")
				);
				if (stack != null) {
					result.add(stack);
				}
			}
		}

		return result;
	}

	private static Trade processTradeQuery(Connection connection, UUID offererUuid, UUID returnerUuid, Timestamp startTime, Timestamp endTime, int resultOrdinal, Array offerUuids, Array returnUuids) throws SQLException {
		Trade.Result result = Trade.Result.values()[resultOrdinal];

		List<CloudItemStack> offerStacks = loadItemStacks(connection, offerUuids);
		List<CloudItemStack> returnStacks = loadItemStacks(connection, returnUuids);

		Trade trade = new Trade(
			startTime.toLocalDateTime(),
			offererUuid, returnerUuid,
			offerStacks, returnStacks
		);
		trade.result = result;
		if (endTime != null) {
			trade.end = endTime.toLocalDateTime();
		}

		return trade;
	}

	public Trade loadTrade(Connection connection, UUID uuid) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
			"select offerer_uuid, returner_uuid, start_time, end_time, result_ordinal, offer_stacks, return_stacks from trades where uuid = ?"
		)) {
			ps.setObject(1, uuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}

			return processTradeQuery(
				connection,
				rs.getObject("offerer_uuid", UUID.class),
				rs.getObject("returner_uuid", UUID.class),
				rs.getTimestamp("start_time"),
				rs.getTimestamp("end_time"),
				rs.getInt("result_ordinal"),
				rs.getArray("offer_stacks"),
				rs.getArray("return_stacks")
			);
		}
	}

	public Map<UUID, Trade> loadPlayerTrades(Connection connection, UUID playerUuid) throws SQLException {
		Map<UUID, Trade> result = new HashMap<>();

		try (PreparedStatement ps = connection.prepareStatement(
			"select uuid, offerer_uuid, returner_uuid, start_time, end_time, result_ordinal, offer_stacks, return_stacks from trades where offerer_uuid = ? or returner_uuid = ?"
		)) {
			ps.setObject(1, playerUuid);
			ps.setObject(2, playerUuid);

			@Cleanup
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result.put(rs.getObject("uuid", UUID.class), processTradeQuery(
					connection,
					rs.getObject("offerer_uuid", UUID.class),
					rs.getObject("returner_uuid", UUID.class),
					rs.getTimestamp("start_time"),
					rs.getTimestamp("end_time"),
					rs.getInt("result_ordinal"),
					rs.getArray("offer_stacks"),
					rs.getArray("return_stacks")
				));
			}
		}

		return result;
	}
}
