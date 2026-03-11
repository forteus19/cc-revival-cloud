package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketPlayerDataRandomDrop extends G_TcpPacketCustomPayload {
	private String username;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		username = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.kickIfNotServer(connectionHandler)) {
			return;
		}
		if (!ServerStatus.REGION.containsKey(connectionHandler.getConnectionUsername())) {
			return;
		}

		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(username);
		if (inventory == null) {
			return;
		}

		Cloud.instanceLog("Giving random drop to %s", username);

		CloudItem drop = CloudItems.getRandomDrop();
		inventory.addItemStack(new CloudItemStack(drop), true);
		connectionHandler.sendPacket(new PacketServerNotification(
			String.format("%s obtained %s%s", username, drop.getRarity().getFormattingCode(), drop.getDisplayName())
		));
	}
}
