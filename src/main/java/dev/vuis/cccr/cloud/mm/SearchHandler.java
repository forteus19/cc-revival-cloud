package dev.vuis.cccr.cloud.mm;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.data.CloudStats;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.packet.mm.PacketMMJoinServer;
import dev.vuis.cccr.cloud.util.Util;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class SearchHandler {
	// TODO configurable
	private static final Map<String, List<String>> VALID_MAPS = Map.ofEntries(
		Map.entry("cas", List.of("Dust", "Haunts", "Mirage", "Toxic")),
		Map.entry("cbd", List.of("Dust", "Mirage", "Toxic")),
		Map.entry("ffa", List.of("Dust", "Haunts", "Laputa", "Mirage", "Range", "Terminal")),
		Map.entry("tdm", List.of("Dome", "Dust", "Haunts", "Laputa", "Mirage", "Nuke", "Range", "Salvage", "Terminal"))
	);

	private static final Map<String, SearchData> ONGOING = new ConcurrentHashMap<>();

	private SearchHandler() {
	}

	private static String getRandomMap(String gameType) {
		List<String> maps = VALID_MAPS.get(gameType);
		if (maps == null) {
			return null;
		}
		return Util.listRandom(maps);
	}

	public static void addSearch(String username, String game, String region, List<String> maps) {
		if (game.equals("cbd") && !CloudStats.compActive) {
			return;
		}

		Cloud.instanceLog("Adding %s to search handler", username);
		ONGOING.put(username, new SearchData(game, region, maps));
	}

	public static void removeSearch(String username) {
		Cloud.instanceLog("Removing %s from search handler", username);
		ONGOING.remove(username);
	}

	public static void onUpdate() {
		if (ServerStatus.REGION.isEmpty()) {
			return;
		}

		ONGOING.entrySet().removeIf(entry -> {
			String username = entry.getKey();
			SearchData searchData = entry.getValue();

			JoinData result = findGame(searchData);
			if (result != null) {
				Cloud.instanceLog("Found match for player %s! (%s - %s)", username, result.server, result.address)
					.sendPacketToPlayer(username, result.createPacket());

				if (result.game.equals("cbd")) {
					PlayerData playerData = DataHandler.instance().getPlayerData(username);
					if (playerData != null) {
						playerData.lastComp.set(result);
					}
				}

				return true;
			} else {
				return false;
			}
		});
	}

	private static JoinData findGame(SearchData searchData) {
		Set<Map.Entry<String, GameStatus>> gameCandidates = new HashSet<>();

		for (Map.Entry<String, GameStatus> gameEntry : ServerStatus.GAME.entrySet()) {
			String server = gameEntry.getKey();
			GameStatus status = gameEntry.getValue();

			if (status.acceptingPlayers &&
				status.remainingPlayerSlots > 0 &&
				canJoinRegion(searchData.region(), ServerStatus.REGION.getOrDefault(server, "ALL")) &&
				searchData.game().equals(status.gameType) &&
				searchData.maps().contains(status.mapType)
			) {
				gameCandidates.add(gameEntry);
			}
		}

		if (gameCandidates.isEmpty()) {
			for (Map.Entry<String, String> entry : ServerStatus.REGION.entrySet()) {
				String entryServer = entry.getKey();
				String entryRegion = entry.getValue();

				if (ServerStatus.GAME.containsKey(entryServer) || !canJoinRegion(searchData.region(), entryRegion)) {
					continue;
				}

				String map = getRandomMap(searchData.game());
				if (map == null) {
					return null;
				}

				return new JoinData(
					entryServer,
					getServerAddress(entryServer),
					searchData.game(),
					map,
					getRandomTeam(searchData.game())
				);
			}

			return null;
		}

		Map.Entry<String, GameStatus> result = gameCandidates.stream()
			.filter(e -> e.getValue().remainingPlayerSlots != 0)
			.min(Comparator.comparingInt((Map.Entry<String, GameStatus> e) -> e.getValue().remainingPlayerSlots))
			.orElseThrow();

		String resultServer = result.getKey();
		GameStatus resultStatus = result.getValue();

		return new JoinData(
			resultServer,
			getServerAddress(resultServer),
			resultStatus.gameType,
			resultStatus.mapType,
			getExistingTeam(resultStatus.teams)
		);
	}
	
	private static boolean canJoinRegion(String selected, String target) {
		if (selected.equalsIgnoreCase("ALL")) {
			return true;
		} else {
			return selected.equalsIgnoreCase(target);
		}
	}

	private static String getServerAddress(String server) {
		return String.format("%s:%d", Cloud.instance().mcHost, ServerStatus.PORT.get(server));
	}

	private static String getRandomTeam(String gameType) {
		return switch (gameType) {
			case "cas", "cbd", "dom", "tdm" -> Util.randomBool() ? "Red" : "Blue";
			case "ffa" -> "Players";
			case "inf" -> Util.randomBool() ? "Dead" : "Living";
			case "sg" -> "Tributes";
			default -> throw new IllegalArgumentException();
		};
	}

	private static String getExistingTeam(List<GameStatus.Team> teams) {
		return teams.stream().filter(team -> !team.name().equals("Spectator")).min(Comparator.comparingInt(GameStatus.Team::players)).orElseThrow().name();
	}

	private record SearchData(
		String game,
		String region,
		List<String> maps
	) {
	}

	public record JoinData(
		String server,
		String address,
		String game,
		String map,
		String team
	) {
		public PacketMMJoinServer createPacket() {
			PacketMMJoinServer packet = new PacketMMJoinServer();
			packet.tag.setString("address", address);
			packet.tag.setString("game", game);
			packet.tag.setString("map", map);
			packet.tag.setString("team", team);
			return packet;
		}
	}
}
