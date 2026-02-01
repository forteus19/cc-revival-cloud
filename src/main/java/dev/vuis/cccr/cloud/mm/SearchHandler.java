package dev.vuis.cccr.cloud.mm;

import com.f3rullo14.cloud.server.G_CloudManager;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.packet.mm.PacketMMJoinServer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class SearchHandler {
	private static final Map<String, List<String>> VALID_MAPS = Map.ofEntries(
		Map.entry("tdm", List.of("Dust"))
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
		Cloud.getInstance().getCloudManager().log("Adding " + username + " to search handler");
		ONGOING.put(username, new Data(game, region, new ArrayList<>(maps)));
	}

	public static void removeSearch(String username) {
		Cloud.getInstance().getCloudManager().log("Removing " + username + " from search handler");
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
				G_CloudManager cloudManager = Cloud.getInstance().getCloudManager();
				cloudManager.log("Found match for player " + username + "! (" + result.tag.getString("address") + ")");
				cloudManager.getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, username, result);
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

			if (!canJoinRegion(data.region(), ServerStatus.REGION.getOrDefault(server, "ALL"))) {
				continue;
			}
			if (!data.game().equals(status.gameType)) {
				continue;
			}
			if (!data.maps().contains(status.mapType)) {
				continue;
			}

			gameCandidates.add(gameEntry);
		}

		if (gameCandidates.isEmpty()) {
			for (Map.Entry<String, String> entry : ServerStatus.REGION.entrySet()) {
				String entryServer = entry.getKey();
				String entryRegion = entry.getValue();

				if (ServerStatus.GAME.containsKey(entryServer)) {
					continue;
				}

				if (canJoinRegion(data.region(), entryRegion)) {
					String map = getRandomMap(data.game());
					if (map == null) {
						return null;
					}

					PacketMMJoinServer packet = new PacketMMJoinServer();
					packet.tag.setString("address", String.format("%s:%d", Cloud.getInstance().mcHost, ServerStatus.PORT.get(entryServer)));
					packet.tag.setString("game", data.game());
					packet.tag.setString("map", map);
					packet.tag.setString("team", getTeam(data.game()));

					return packet;
				}
			}

			return null;
		}

		Optional<Map.Entry<String, GameStatus>> resultOptional = gameCandidates.stream()
			.filter(e -> e.getValue().remainingPlayerSlots != 0)
			.min(Comparator.comparingInt((Map.Entry<String, GameStatus> e) -> e.getValue().remainingPlayerSlots));
		if (resultOptional.isEmpty()) {
			return null;
		}
		Map.Entry<String, GameStatus> result = resultOptional.get();

		String resultServer = result.getKey();
		GameStatus resultStatus = result.getValue();

		PacketMMJoinServer packet = new PacketMMJoinServer();
		packet.tag.setString("address", String.format("%s:%d", Cloud.getInstance().mcHost, ServerStatus.PORT.get(resultServer)));
		packet.tag.setString("game", resultStatus.gameType);
		packet.tag.setString("map", resultStatus.mapType);
		packet.tag.setString("team", getTeam(resultStatus.gameType));

		return packet;
	}
	
	private static boolean canJoinRegion(String selected, String target) {
		if (selected.equalsIgnoreCase("ALL")) {
			return true;
		} else {
			return selected.equalsIgnoreCase(target);
		}
	}

	private static String getTeam(String gameType) {
		return switch (gameType) {
			// TODO use cbd team sizes provided in game status packet
			case "cas", "cbd", "dom", "tdm" -> ThreadLocalRandom.current().nextInt(2) == 0 ? "Red" : "Blue";
			case "ffa" -> "Players";
			case "inf" -> ThreadLocalRandom.current().nextInt(2) == 0 ? "Dead" : "Living";
			case "sg" -> "Tributes";
			default -> throw new IllegalArgumentException();
		};
	}

	private record Data(
		String game,
		String region,
		ArrayList<String> maps
	) {
	}
}
