package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PunishmentType;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketFriendRequest extends G_TcpPacketCustomPayload {
	private String user;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		readString(in);
		user = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		if (user.equals(connectionHandler.getConnectionUsername())) {
			return;
		}

		PlayerData selfData = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (selfData == null) {
			return;
		}
		if (selfData.hasActivePunishment(PunishmentType.BAN_CLOUD)) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"Disabled due to cloud ban.", CloudConstants.CC_NAME, 1
			));
			return;
		}

		PlayerData targetData = DataHandler.instance().getPlayerData(user);
		if (targetData == null) {
			return;
		}
		if (targetData.hasActivePunishment(PunishmentType.BAN_CLOUD)) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"You cannot send a friend request to a banned player!", CloudConstants.CC_NAME, 0
			));
			return;
		}

		targetData.friendRequests.add(DataHandler.instance().getPlayerUuid(connectionHandler.getConnectionUsername()));

		Cloud.instance().sendPacketToPlayer(user, new PacketNotificationFromCloud(
			"Received friend request from " + connectionHandler.getConnectionUsername() + "!", CloudConstants.CC_NAME, 0
		));
		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			"Sent friend request to " + user + "!", CloudConstants.CC_NAME, 0
		));
	}
}
