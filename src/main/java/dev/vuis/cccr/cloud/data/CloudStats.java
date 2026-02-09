package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import java.util.Comparator;
import java.util.List;

public final class CloudStats {
	private static final int SCOREBOARD_TAG_LIMIT = 30;

	private static final Multiset<String> PLAYER_SCOREBOARD = HashMultiset.create();

	public static boolean compRequiresRank = false;
	public static boolean compActive = false;

	private CloudStats() {
	}

	public static void incrementPlayerScore(String name) {
		PLAYER_SCOREBOARD.add(name);
	}

	public static void writeTag(FDSTagCompound tag) {
		tag.setInteger("usersonline", Cloud.getInstance().getCloudManager().getNetwork().getNetChannel(CloudConstants.CHANNEL_PLAYER).size());
		tag.setBoolean("compRequiresRank", compRequiresRank);
		tag.setBoolean("compactive", compActive);

		Multiset<String> gamePlayerCounts = HashMultiset.create();
		for (GameStatus status : ServerStatus.GAME.values()) {
			gamePlayerCounts.add(status.gameType, status.numPlayers);
		}
		for (Multiset.Entry<String> entry : gamePlayerCounts.entrySet()) {
			tag.setInteger(entry.getElement() + "count", entry.getCount());
		}

		int numPlayerScores = Math.min(SCOREBOARD_TAG_LIMIT, PLAYER_SCOREBOARD.size());
		tag.setInteger("playerScoreCount", numPlayerScores);

		List<Multiset.Entry<String>> sbEntries = PLAYER_SCOREBOARD.entrySet().stream()
			.sorted(Comparator.comparingInt((Multiset.Entry<String> e) -> e.getCount()).reversed())
			.limit(SCOREBOARD_TAG_LIMIT)
			.toList();
		for (int i = 0; i < sbEntries.size(); i++) {
			Multiset.Entry<String> entry = sbEntries.get(i);
			tag.setString("player_" + i, entry.getElement());
			tag.setInteger("player_score_" + i, entry.getCount());
		}
	}
}
