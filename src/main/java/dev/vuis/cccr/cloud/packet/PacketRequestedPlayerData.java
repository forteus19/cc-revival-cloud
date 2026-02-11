package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketRequestedPlayerData extends G_TcpPacketCustomPayload {
	public String username;
	public FDSTagCompound tag = new FDSTagCompound("playerdata");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		writeString(out, username);
		tag.write(out);
	}

	@Override
	public void readPacketFromStream(DataInputStream in) {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler var1) {
	}

	public static void sendForPlayer(G_ITcpConnectionHandler connectionHandler, String username) {
		PlayerData data = DataMaps.getPlayerData(username);
		if (data == null) {
			return;
		}

		PacketRequestedPlayerData packet = new PacketRequestedPlayerData();
		packet.username = data.username.get();
		data.writeDataTag(packet.tag);

		connectionHandler.sendPacket(packet);
	}
}
