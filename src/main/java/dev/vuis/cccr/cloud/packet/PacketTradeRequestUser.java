package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PunishmentType;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketTradeRequestUser extends G_TcpPacketCustomPayload {
	private String target;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		readString(in);
		target = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		if (connectionHandler.getConnectionUsername().equalsIgnoreCase(target)) {
			return;
		}

		PlayerData requesterData = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (requesterData == null) {
			return;
		}
		if (requesterData.hasActivePunishment(PunishmentType.BAN_TRADING, PunishmentType.BAN_CLOUD)) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"Unable to create trade due to trading ban.", CloudConstants.CC_NAME, 1
			));
			return;
		}

		PlayerData targetData = DataHandler.instance().getPlayerData(target);
		if (targetData == null) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				String.format("Player %s not found!", target), CloudConstants.CC_NAME, 0
			));
			return;
		}
		if (targetData.hasActivePunishment(PunishmentType.BAN_TRADING, PunishmentType.BAN_CLOUD)) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"This player has an active trading ban!", CloudConstants.CC_NAME, 0
			));
			return;
		}

		connectionHandler.sendPacket(new PacketTradeRequestedUser(target));
		PacketRequestedInventory.sendForPlayer(connectionHandler, target);
	}
}
