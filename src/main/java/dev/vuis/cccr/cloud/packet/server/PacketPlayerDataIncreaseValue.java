package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketPlayerDataIncreaseValue extends G_TcpPacketCustomPayload {
	private String username;
	private String value;
	private int amount;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		username = readString(in);
		value = readString(in);
		amount = in.readInt();
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.kickIfServer(connectionHandler)) {
			return;
		}
		if (!ServerStatus.GAME.containsKey(connectionHandler.getConnectionUsername())) {
			return;
		}

		Cloud.instanceLog("INCREASE VALUE %s %s %d", username, value, amount);

		PlayerData playerData = DataMaps.getPlayerData(username, false);

		switch (value) {
			case "deaths" -> playerData.deaths.addAndGet(amount);
			case "emeralds" -> playerData.emeralds.addAndGet(amount);
			case "firstblood" -> playerData.firstBloods.addAndGet(amount);
			case "games" -> playerData.games.addAndGet(amount);
			case "kills" -> playerData.kills.addAndGet(amount);
			case "trophies" -> playerData.trophies.addAndGet(amount);
		}
	}
}
