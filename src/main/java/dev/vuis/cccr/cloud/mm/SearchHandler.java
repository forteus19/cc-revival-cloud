package dev.vuis.cccr.cloud.mm;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.packet.mm.PacketMMJoinServer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class SearchHandler {
	// TODO configurable
	private static final Map<String, List<String>> VALID_MAPS = Map.ofEntries(
		Map.entry("cas", List.of("Dust", "Mirage", "Toxic")),
		Map.entry("ffa", List.of("Dust", "Laputa")),
		Map.entry("tdm", List.of("Dome", "Dust", "Laputa", "Mirage", "Nuke", "Range", "Salvage", "Terminal"))
	);

	private static final Map<String, Data> ONGOING = new ConcurrentHashMap<>();

	private SearchHandler() {
	}

	private static String getRandomMap(String gameType) {
		List<String> maps = VALID_MAPS.get(gameType);
		if (maps == null) {
			return null;
		}
		return maps.get(ThreadLocalRandom.current().nextInt(maps.size()));
	}

	public static void addSearch(String username, String game, String region, ArrayList<String> maps) {
		Cloud.instanceLog("Adding %s to search handler", username);
		ONGOING.put(username, new Data(game, region, new ArrayList<>(maps)));
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
			Data data = entry.getValue();

			PacketMMJoinServer result = findOngoingGame(data);
			if (result != null) {
				Cloud.instanceLog("Found match for player %s! (%s)", username, result.tag.getString("address"))
					.sendPacketToPlayer(username, result);
				return true;
			} else {
				return false;
			}
		});
	}

	private static PacketMMJoinServer findOngoingGame(Data data) {
		Set<Map.Entry<String, GameStatus>> gameCandidates = new HashSet<>();

		for (Map.Entry<String, GameStatus> gameEntry : ServerStatus.GAME.entrySet()) {
			String server = gameEntry.getKey();
			GameStatus status = gameEntry.getValue();

			if (canJoinRegion(data.region(), ServerStatus.REGION.getOrDefault(server, "ALL")) &&
				data.game().equals(status.gameType) &&
				data.maps().contains(status.mapType) &&
				status.remainingPlayerSlots > 0
			) {
				gameCandidates.add(gameEntry);
			}
		}

		if (gameCandidates.isEmpty()) {
			for (Map.Entry<String, String> entry : ServerStatus.REGION.entrySet()) {
				String entryServer = entry.getKey();
				String entryRegion = entry.getValue();

				if (ServerStatus.GAME.containsKey(entryServer) || !canJoinRegion(data.region(), entryRegion)) {
					continue;
				}

				String map = getRandomMap(data.game());
				if (map == null) {
					return null;
				}

				PacketMMJoinServer packet = new PacketMMJoinServer();
				packet.tag.setString("address", getServerAddress(entryServer));
				packet.tag.setString("game", data.game());
				packet.tag.setString("map", map);
				packet.tag.setString("team", getRandomTeam(data.game()));

				return packet;
			}

			return null;
		}

		Map.Entry<String, GameStatus> result = gameCandidates.stream()
			.filter(e -> e.getValue().remainingPlayerSlots != 0)
			.min(Comparator.comparingInt((Map.Entry<String, GameStatus> e) -> e.getValue().remainingPlayerSlots))
			.orElseThrow();

		String resultServer = result.getKey();
		GameStatus resultStatus = result.getValue();

		PacketMMJoinServer packet = new PacketMMJoinServer();
		packet.tag.setString("address", getServerAddress(resultServer));
		packet.tag.setString("game", resultStatus.gameType);
		packet.tag.setString("map", resultStatus.mapType);
		packet.tag.setString("team", getExistingTeam(resultStatus.teams));

		return packet;
	}
	
	private static boolean canJoinRegion(String selected, String target) {
		if (selected.equalsIgnoreCase("ALL")) {
			return true;
		} else {
			return selected.equalsIgnoreCase(target);
		}
	}

	private static String getServerAddress(String server) {
		return String.format("%s:%d", Cloud.getInstance().mcHost, ServerStatus.PORT.get(server));
	}

	private static String getRandomTeam(String gameType) {
		return switch (gameType) {
			case "cas", "cbd", "dom", "tdm" -> ThreadLocalRandom.current().nextInt(2) == 0 ? "Red" : "Blue";
			case "ffa" -> "Players";
			case "inf" -> ThreadLocalRandom.current().nextInt(2) == 0 ? "Dead" : "Living";
			case "sg" -> "Tributes";
			default -> throw new IllegalArgumentException();
		};
	}

	private static String getExistingTeam(List<GameStatus.Team> teams) {
		return teams.stream().filter(team -> !team.name().equals("Spectator")).min(Comparator.comparingInt(GameStatus.Team::players)).orElseThrow().name();
	}

	private record Data(
		String game,
		String region,
		ArrayList<String> maps
	) {
	}
}
