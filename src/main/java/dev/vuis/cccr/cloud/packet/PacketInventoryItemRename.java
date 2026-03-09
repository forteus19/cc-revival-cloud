package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public final class PacketInventoryItemRename extends G_TcpPacketCustomPayload {
	private UUID tagUuid;
	private UUID renamingUuid;
	private String name;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		tagUuid = UUID.fromString(readString(in));
		renamingUuid = UUID.fromString(readString(in));
		name = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(connectionHandler.getConnectionUsername());
		if (inventory == null) {
			return;
		}

		CloudItemStack tagStack = inventory.getItemStack(tagUuid);
		if (tagStack == null || tagStack.item != CloudItems.NAME_TAG) {
			return;
		}
		CloudItemStack renamingStack = inventory.getItemStack(renamingUuid);
		if (renamingStack == null || !renamingStack.item.isNamable()) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"That item is not renamable!", CloudConstants.CC_NAME, 0
			));
			return;
		}

		renamingStack.nametag = name;
		inventory.removeItemStack(tagUuid, true);

		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			"Successfully renamed item!", CloudConstants.CC_NAME, 0
		));
		PacketRequestedInventory.sendForPlayer(connectionHandler, connectionHandler.getConnectionUsername());
	}
}
