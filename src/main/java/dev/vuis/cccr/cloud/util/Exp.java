package dev.vuis.cccr.cloud.util;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerRank;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.server.PacketServerNotification;

public final class Exp {
	public static final int MATCH_PARTICIPATE = 1500;
	public static final int TROPHY = 450;
	public static final int KILL = 60;
	public static final int FIRST_BLOOD = 80;
	public static final int DEATH = 10;

	private Exp() {
	}

	public static void handleExpGain(G_ITcpConnectionHandler serverConnection, PlayerData playerData, int expGain) {
		String username = playerData.username.get();

		PlayerRank prevRank = PlayerRank.getRankFromExp(playerData.exp.get());
		playerData.dirty.mark();
		PlayerRank newRank = PlayerRank.getRankFromExp(playerData.exp.addAndGet(expGain));

		if (prevRank != newRank) {
			serverConnection.sendPacket(new PacketServerNotification(
				String.format("%s ranked up to %s", username, newRank.getDisplayName())
			));
			Cloud.instance().sendPacketToPlayer(username, new PacketNotificationFromCloud(
				String.format("You have ranked up to %s!", newRank.getDisplayName()), CloudConstants.CC_NAME, 0
			));
		}
	}
}
