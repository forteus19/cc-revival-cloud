package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketFriendPoke extends G_TcpPacketCustomPayload {
	private String user;
	private String message;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		user = readString(in);
		message = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData targetData = DataHandler.instance().getPlayerData(user);
		if (targetData == null) {
			return;
		}

		if (!targetData.friends.contains(targetData.uuid)) {
			return;
		}

		Cloud.instance().sendPacketToPlayer(user, new PacketNotificationFromCloud(
			"Poke from " + connectionHandler.getConnectionUsername() + ": " + message, CloudConstants.CC_NAME, 0
		));
		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			"Sent poke to " + user + "!", CloudConstants.CC_NAME, 0
		));
	}
}
