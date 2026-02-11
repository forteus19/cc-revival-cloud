package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketEditMood extends G_TcpPacketCustomPayload {
	private String mood;

	@Override
	public void writePacketToStream(DataOutputStream out) {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		mood = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData playerData = DataMaps.getPlayerData(connectionHandler.getConnectionUsername());
		if (playerData == null) {
			return;
		}
		playerData.mood.set(mood);
	}
}
