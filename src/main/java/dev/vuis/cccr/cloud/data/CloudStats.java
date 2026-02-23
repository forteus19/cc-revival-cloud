package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.Pair;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class CloudStats {
	public static final int SCOREBOARD_LIMIT = 30;
	public static final AtomicReference<List<Pair<String, Integer>>> PLAYER_SCOREBOARD = new AtomicReference<>(List.of());
	public static final AtomicReference<LocalDateTime> SCOREBOARD_RESET_TIME = new AtomicReference<>(computeScoreboardResetTime());

	public static boolean compRequiresRank = true;
	public static boolean compActive = true;

	private CloudStats() {
	}

	public static void writeTag(FDSTagCompound tag) {
		tag.setInteger("usersonline", Cloud.instance().getCloudManager().getNetwork().getNetChannel(CloudConstants.CHANNEL_PLAYER).size());
		tag.setBoolean("compRequiresRank", compRequiresRank);
		tag.setBoolean("compactive", compActive);

		Multiset<String> gamePlayerCounts = HashMultiset.create();
		for (GameStatus status : ServerStatus.GAME.values()) {
			gamePlayerCounts.add(status.gameType, status.numPlayers);
		}
		for (Multiset.Entry<String> entry : gamePlayerCounts.entrySet()) {
			tag.setInteger(entry.getElement() + "count", entry.getCount());
		}

		tag.setString("scoreboardResetTime", Long.toString(SCOREBOARD_RESET_TIME.get().toInstant(ZoneOffset.UTC).toEpochMilli()));

		List<Pair<String, Integer>> playerSbGet = PLAYER_SCOREBOARD.get();

		tag.setInteger("playerScoreCount", playerSbGet.size());
		for (int i = 0; i < playerSbGet.size(); i++) {
			Pair<String, Integer> entry = playerSbGet.get(i);
			tag.setString("player_" + i, entry.left());
			tag.setInteger("player_score_" + i, entry.right());
		}
	}

	public static LocalDateTime computeScoreboardResetTime() {
		return LocalDate.now(ZoneOffset.UTC)
			.with(TemporalAdjusters.firstDayOfNextMonth())
			.atStartOfDay();
	}
}
