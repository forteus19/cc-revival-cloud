package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.RequestType;
import dev.vuis.cccr.cloud.data.CloudStats;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.MarketStore;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.packet.market.PacketRequestedMarketStoreItems;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public final class PacketClientRequest extends G_TcpPacketCustomPayload {
	private RequestType type;
	private String[] requestData;

	@Override
	public void writePacketToStream(DataOutputStream out) {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		String typeStr = readString(in);
		type = Arrays.stream(RequestType.values()).filter(requestType -> requestType.toString().equals(typeStr)).findFirst().orElseThrow();
		if (in.readBoolean()) {
			requestData = new String[in.readInt()];
			for (int i = 0; i < requestData.length; i++) {
				requestData[i] = readString(in);
			}
		}
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
		G_CloudTcpConnectionHandler cloudHandler = (G_CloudTcpConnectionHandler) connectionHandler;
		if (cloudHandler.channel.equals(CloudConstants.CHANNEL_PLAYER) && PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		switch (type) {
			case PLAYER_DATA -> {
				if (requestData.length != 1) {
					break;
				}
				PacketRequestedPlayerData.sendForPlayer(connectionHandler, requestData[0]);
			}
			case PLAYER_INVENTORY -> {
				if (requestData.length != 1) {
					break;
				}
				PacketRequestedInventory.sendForPlayer(connectionHandler, requestData[0]);
			}
			case PLAYER_INVENTORY_DEFAULTS -> {
				if (requestData.length != 1) {
					break;
				}
				String username = requestData[0];

				for (CloudItemCategory category : CloudItemCategory.values()) {
					PacketRequestedItemDefault.sendForPlayer(connectionHandler, username, category, false);
				}
			}
			case PLAYER_FRIENDS -> {
				if (requestData.length != 1) {
					break;
				}
				PlayerData playerData = DataMaps.getPlayerData(requestData[0], false);
				if (playerData == null) {
					break;
				}

				PacketRequestedFriends packet = new PacketRequestedFriends();
				playerData.writeFriendsTag(packet.tag);
				connectionHandler.sendPacket(packet);
			}
			case CLOUDSTATS -> {
				PacketRequestedCloudData packet = new PacketRequestedCloudData();
				CloudStats.writeTag(packet.tag);
				connectionHandler.sendPacket(packet);
			}
			case STOREITEMS -> {
				PacketRequestedMarketStoreItems packet = new PacketRequestedMarketStoreItems();
				MarketStore.writeTag(packet.tag);
				connectionHandler.sendPacket(packet);
			}
		}
	}
}
