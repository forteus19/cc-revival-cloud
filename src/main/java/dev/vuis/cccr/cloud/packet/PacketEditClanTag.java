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

public final class PacketEditClanTag extends G_TcpPacketCustomPayload {
	private String clanTag;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		clanTag = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData playerData = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (playerData == null) {
			return;
		}
		if (playerData.hasActivePunishment(PunishmentType.BAN_CLOUD)) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"Disabled due to cloud ban.", CloudConstants.CC_NAME, 1
			));
			return;
		}

		if (clanTag.length() > 4) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"Invalid clan tag!", CloudConstants.CC_NAME, 1
			));
		}

		playerData.dirty.mark();
		playerData.clanTag.set(clanTag.isEmpty() ? null : clanTag);
	}
}
