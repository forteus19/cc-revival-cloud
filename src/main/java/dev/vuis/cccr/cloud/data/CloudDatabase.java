package dev.vuis.cccr.cloud.data;

import dev.vuis.cccr.cloud.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;
import javax.sql.DataSource;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CloudDatabase {
	private final DataSource dataSource;

	public void savePlayerData(PlayerData data, boolean ignoreDirty) throws SQLException {
		@Cleanup
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);

		if (ignoreDirty || data.dirty.isDirty()) {
			try (PreparedStatement ps = connection.prepareStatement(
				"insert into players (uuid, username, trophies, kills, deaths, games, first_bloods, match_karma, minutes_played, exp, emeralds, mood, achievements, group_tag) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
					"on conflict (uuid) do update set " +
					"username = excluded.username, trophies = excluded.trophies, kills = excluded.kills, deaths = excluded.deaths, " +
					"games = excluded.games, first_bloods = excluded.first_bloods, match_karma = excluded.match_karma, minutes_played = excluded.minutes_played, " +
					"exp = excluded.exp, emeralds = excluded.emeralds, mood = excluded.mood, achievements = excluded.achievements, " +
					"group_tag = excluded.group_tag"
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
				ps.setString(14, Util.ifNonNull(data.group.get(), PlayerGroup::name));

				ps.executeUpdate();
			}

			data.dirty.clear();
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

					ps.executeUpdate();
				}
			}

			data.friendsDirty.clear();
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

					ps.executeUpdate();
				}
			}

			data.friendRequestsDirty.clear();
		}

		connection.commit();
	}

	public PlayerData loadPlayerData(UUID uuid) throws SQLException {
		@Cleanup
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);

		PlayerData data;

		try (PreparedStatement ps = connection.prepareStatement(
			"select username, trophies, kills, deaths, games, first_bloods, match_karma, minutes_played, exp, emeralds, mood, achievements, group_tag from players where uuid = ?"
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
			data.group.set(PlayerGroups.getByTag(rs.getString("group_tag")));
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

		connection.commit();

		return data;
	}
}
