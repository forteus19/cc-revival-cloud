package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketFriendRequestAccept extends G_TcpPacketCustomPayload {
	private String user;
	private int action;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		readString(in);
		user = readString(in);
		action = in.readInt();
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData accepterData = DataMaps.getPlayerData(connectionHandler.getConnectionUsername());
		if (accepterData == null) {
			return;
		}
		PlayerData requesterData = DataMaps.getPlayerData(user);
		if (requesterData == null) {
			return;
		}

		if (!accepterData.friendRequests.contains(user)) {
			return;
		}

		if (action == 2) {
			connectionHandler.sendPacket(
				new PacketNotificationFromCloud("Ignored friend request from " + user + "!", CloudConstants.CC_NAME, 0)
			);
			return;
		}

		accepterData.friendRequests.remove(user);
		accepterData.friends.add(user);
		requesterData.friends.add(connectionHandler.getConnectionUsername());

		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			"Accepted friend request from " + user + "!", CloudConstants.CC_NAME, 0
		));
		Cloud.getInstance().sendPacketToPlayer(user, new PacketNotificationFromCloud(
			connectionHandler.getConnectionUsername() + " accepted your friend request!", CloudConstants.CC_NAME, 0
		));
	}
}
