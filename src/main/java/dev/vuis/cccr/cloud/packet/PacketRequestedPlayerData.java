package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
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
		PlayerData data = DataHandler.instance().getPlayerData(username);
		if (data == null) {
			return;
		}

		int coinId = 0;
		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(username);
		if (inventory != null) {
			CloudItemStack coinStack = inventory.getDefaultStack(CloudItemCategory.COIN, null);
			if (coinStack != null) {
				coinId = coinStack.item.getId();
			}
		}

		String dataUsername = data.username.get();

		PacketRequestedPlayerData packet = new PacketRequestedPlayerData();
		packet.username = dataUsername;
		data.writeDataTag(packet.tag, coinId, connectionHandler.getConnectionUsername().equals(dataUsername));

		connectionHandler.sendPacket(packet);
	}
}
