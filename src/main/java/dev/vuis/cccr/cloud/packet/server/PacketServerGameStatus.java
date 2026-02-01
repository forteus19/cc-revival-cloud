package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.data.server.GameStatus;
import dev.vuis.cccr.cloud.data.server.ServerStatus;
import dev.vuis.cccr.cloud.util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketServerGameStatus extends G_TcpPacketCustomPayload {
	private String gameType;
	private String mapType;
	private int remainingPlayerSlots;
	private int remainingSpectatorSlots;
	private int numPlayers;
	private boolean acceptingPlayers;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		boolean hasGame = in.readBoolean();
		readString(in);
		if (!hasGame) {
			return;
		}

		gameType = readString(in);
		mapType = readString(in);
		remainingPlayerSlots = in.readInt();
		remainingSpectatorSlots = in.readInt();
		numPlayers = in.readInt();
		acceptingPlayers = in.readBoolean();
		in.readInt();
		in.readInt();
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (Util.kickIfServer(connectionHandler)) {
			return;
		}
		GameStatus game = ServerStatus.GAME.get(connectionHandler.getConnectionUsername());
		if (game != null) {
			game.gameType = gameType;
			game.mapType = mapType;
			game.remainingPlayerSlots = remainingPlayerSlots;
			game.remainingSpectatorSlots = remainingSpectatorSlots;
			game.numPlayers = numPlayers;
			game.acceptingPlayers = acceptingPlayers;
		}
	}
}
