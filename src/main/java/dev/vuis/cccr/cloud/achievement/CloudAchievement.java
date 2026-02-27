package dev.vuis.cccr.cloud.achievement;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.server.PacketServerNotification;

public record CloudAchievement(
	int id,
	String name
) {
	public CloudAchievement {
		CloudAchievements.ID_MAP.put(id, this);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CloudAchievement ach && id == ach.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}

	public void handleUnlock(PlayerData playerData, G_ITcpConnectionHandler serverConnection) {
		if (playerData.achievements.add(id)) {
			playerData.dirty.mark();
		} else {
			return;
		}
		Cloud.instance().sendPacketToPlayer(playerData.uuid, new PacketNotificationFromCloud(
			String.format("You've unlocked a new Achievement! (%s)", name), CloudConstants.CC_NAME, 0
		));
		if (serverConnection != null) {
			serverConnection.sendPacket(new PacketServerNotification(
				String.format("%s has earned the achievement §f%s", playerData.username.get(), name)
			));
		}
	}
}
