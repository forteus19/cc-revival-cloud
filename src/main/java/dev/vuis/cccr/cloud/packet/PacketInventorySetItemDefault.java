package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public final class PacketInventorySetItemDefault extends G_TcpPacketCustomPayload {
	private UUID stackUuid;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		stackUuid = UUID.fromString(readString(in));
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		PlayerInventory inventory = DataMaps.getPlayerInventory(connectionHandler.getConnectionUsername(), false);
		if (inventory == null) {
			return;
		}
		inventory.setDefaultStack(stackUuid);
	}
}
