package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
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
		if (user.equals(connectionHandler.getConnectionUsername())) {
			return;
		}

		PlayerData targetData = DataMaps.getPlayerData(user, false);
		if (targetData == null) {
			return;
		}

		targetData.friendRequests.add(connectionHandler.getConnectionUsername());

		connectionHandler.sendPacket(
			new PacketNotificationFromCloud("Sent friend request to " + user + "!", CloudConstants.CC_NAME, 0)
		);
		Cloud.getInstance().getCloudManager().getNetwork().sendPacketToClient(
			CloudConstants.CHANNEL_PLAYER, user,
			new PacketNotificationFromCloud("Received friend request from " + connectionHandler.getConnectionUsername() + "!", CloudConstants.CC_NAME, 0)
		);
	}
}
