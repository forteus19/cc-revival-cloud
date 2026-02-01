package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.IDataHolder;
import dev.vuis.cccr.cloud.data.IPlayerData;
import dev.vuis.cccr.cloud.main.Cloud;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
		IDataHolder dataHolder = Cloud.getInstance().getDataHolder();

		IPlayerData accepterData = dataHolder.getPlayerData(connectionHandler.getConnectionUsername(), false);
		if (accepterData == null) {
			return;
		}
		IPlayerData requesterData = dataHolder.getPlayerData(user, false);
		if (requesterData == null) {
			return;
		}

		ArrayList<String> friendRequests = accepterData.getFriendRequests();
		if (!friendRequests.contains(user)) {
			return;
		}

		if (action == 2) {
			connectionHandler.sendPacket(
				new PacketNotificationFromCloud("Ignored friend request from " + user + "!", CloudConstants.CC_NAME, 0)
			);
			return;
		}

		friendRequests.remove(user);
		accepterData.getFriends().add(user);
		requesterData.getFriends().add(connectionHandler.getConnectionUsername());

		connectionHandler.sendPacket(
			new PacketNotificationFromCloud("Accepted friend request from " + user + "!", CloudConstants.CC_NAME, 0)
		);
		Cloud.getInstance().getCloudManager().getNetwork().sendPacketToClient(
			"player", user,
			new PacketNotificationFromCloud(connectionHandler.getConnectionUsername() + " accepted your friend request!", CloudConstants.CC_NAME, 0)
		);
	}
}
