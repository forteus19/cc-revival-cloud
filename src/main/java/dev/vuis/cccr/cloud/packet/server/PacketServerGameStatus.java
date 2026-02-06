package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketServerGameStatus extends G_TcpPacketCustomPayload {
	private boolean hasGame;
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
		hasGame = in.readBoolean();
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
		if (PacketUtil.kickIfServer(connectionHandler)) {
			return;
		}
		if (!ServerStatus.REGION.containsKey(connectionHandler.getConnectionUsername())) {
			return;
		}

		if (!hasGame) {
			ServerStatus.GAME.remove(connectionHandler.getConnectionUsername());
			return;
		}

		GameStatus game = ServerStatus.GAME.computeIfAbsent(connectionHandler.getConnectionUsername(), k -> new GameStatus());
		game.gameType = gameType;
		game.mapType = mapType;
		game.remainingPlayerSlots = remainingPlayerSlots;
		game.remainingSpectatorSlots = remainingSpectatorSlots;
		game.numPlayers = numPlayers;
		game.acceptingPlayers = acceptingPlayers;
	}
}
