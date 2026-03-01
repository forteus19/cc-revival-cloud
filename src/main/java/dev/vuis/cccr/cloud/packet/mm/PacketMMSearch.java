package dev.vuis.cccr.cloud.packet.mm;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.CloudStats;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerRank;
import dev.vuis.cccr.cloud.data.PunishmentType;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PacketMMSearch extends G_TcpPacketCustomPayload {
	private String game;
	private String region;
	private List<String> maps;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		game = readString(in);
		region = readString(in);

		int numMaps = in.readInt();
		maps = new ArrayList<>(numMaps);
		for (int i = 0; i < numMaps; i++) {
			maps.add(readString(in));
		}
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
		if (data.hasActivePunishment(PunishmentType.BAN_MM, PunishmentType.BAN_CLOUD)) {
			connectionHandler.sendPacket(new PacketMMSearchCloudCanceled());
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"Unable to search due to matchmaking ban.", CloudConstants.CC_NAME, 1
			));
			return;
		}

		if (game.equals("cbd")) {
			if (!CloudStats.compActive) {
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"Competitive is currently disabled!", CloudConstants.CC_NAME, 0
				));
				return;
			}
			if (CloudStats.compRequiresRank && PlayerRank.getRankFromExp(data.exp.get()).ordinal() < PlayerRank.OCELOT.ordinal()) {
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"You must be at least Ocelot to join competitive!", CloudConstants.CC_NAME, 0
				));
				return;
			}
		}

		SearchHandler.addSearch(connectionHandler.getConnectionUsername(), game, region, maps);
	}
}
