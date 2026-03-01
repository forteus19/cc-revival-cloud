package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketCompetitiveLeave extends G_TcpPacketCustomPayload {
	private String username;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		username = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.kickIfNotServer(connectionHandler)) {
			return;
		}
		GameStatus status = ServerStatus.GAME.get(connectionHandler.getConnectionUsername());
		if (status == null || !status.gameType.equals("cbd")) {
			return;
		}

		PlayerData data = DataHandler.instance().getPlayerData(username);
		if (data == null) {
			return;
		}

		data.compRejoin.set(true);
	}
}
