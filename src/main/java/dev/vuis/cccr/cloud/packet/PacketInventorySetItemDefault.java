package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.util.PacketUtil;
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
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerInventory inventory = DataMaps.getPlayerInventory(connectionHandler.getConnectionUsername());
		if (inventory == null) {
			return;
		}

		if (inventory.isDefaultStack(stackUuid)) {
			CloudItemCategory removedCategory = inventory.clearDefaultStack(stackUuid);
			if (removedCategory != null) {
				PacketRequestedItemDefault.sendForPlayer(connectionHandler, connectionHandler.getConnectionUsername(), removedCategory, true);
			}
		} else {
			inventory.setDefaultStack(stackUuid);
		}
	}
}
