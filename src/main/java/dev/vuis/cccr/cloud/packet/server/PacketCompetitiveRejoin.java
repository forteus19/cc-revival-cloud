package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PunishmentType;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketCompetitiveRejoin extends G_TcpPacketCustomPayload {
	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData data = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (data == null) {
			return;
		}
		if (data.hasActivePunishment(PunishmentType.BAN_CLOUD, PunishmentType.BAN_MM)) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"Unable to rejoin due to matchmaking ban.", CloudConstants.CC_NAME, 1
			));
			return;
		}

		SearchHandler.JoinData lastComp = data.lastComp.get();
		if (lastComp != null) {
			connectionHandler.sendPacket(lastComp.createPacket());
		}
	}
}
