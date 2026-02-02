package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.RequestType;
import dev.vuis.cccr.cloud.data.CloudStats;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.util.Util;
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
		switch (type) {
			case PLAYER_DATA -> {
				if (requestData.length != 1) {
					break;
				}
				PlayerData playerData = DataMaps.getPlayerData(requestData[0], false);
				if (playerData == null) {
					break;
				}
				if (playerData.uuid.get().equals(Util.NULL_UUID)) {
					break;
				}

				PacketRequestedPlayerData packet = new PacketRequestedPlayerData();
				packet.username = requestData[0];
				playerData.putDataTag(packet.tag);
				connectionHandler.sendPacket(packet);
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
				playerData.putFriendsTag(packet.tag);
				connectionHandler.sendPacket(packet);
			}
			case CLOUDSTATS -> {
				PacketRequestedCloudData packet = new PacketRequestedCloudData();
				CloudStats.putTag(packet.tag);
				connectionHandler.sendPacket(packet);
			}
		}
	}
}
