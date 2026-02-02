package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.IDataHolder;
import dev.vuis.cccr.cloud.data.IPlayerData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketFriendRemove extends G_TcpPacketCustomPayload {
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
		IDataHolder dataHolder = Cloud.getInstance().getDataHolder();

		IPlayerData selfData = dataHolder.getPlayerData(connectionHandler.getConnectionUsername(), false);
		if (selfData == null) {
			return;
		}
		IPlayerData targetData = dataHolder.getPlayerData(user, false);
		if (targetData == null) {
			return;
		}

		selfData.getFriends().remove(user);
		targetData.getFriends().remove(user);

		connectionHandler.sendPacket(
			new PacketNotificationFromCloud("Removed friend " + user + "!", CloudConstants.CC_NAME, 0)
		);
	}
}
