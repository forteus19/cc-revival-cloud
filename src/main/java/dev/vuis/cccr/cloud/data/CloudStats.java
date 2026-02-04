package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;

public final class CloudStats {
	public static boolean compRequiresRank = false;
	public static boolean compActive = false;

	private CloudStats() {
	}

	public static void putTag(FDSTagCompound tag) {
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
	}
}
