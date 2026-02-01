package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.data.IPlayerData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketEditMood extends G_TcpPacketCustomPayload {
	public String mood;

	@Override
	public void writePacketToStream(DataOutputStream out) {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		mood = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
		IPlayerData playerData = Cloud.getInstance().getDataHolder().getPlayerData(connectionHandler.getConnectionUsername(), false);
		if (playerData == null) {
			return;
		}
		playerData.setMood(mood);
	}
}
