package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCase;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class PacketInventoryOpenCase extends G_TcpPacketCustomPayload {
	private UUID uuid;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		uuid = UUID.fromString(readString(in));
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		PlayerInventory inventory = DataMaps.getPlayerInventory(connectionHandler.getConnectionUsername(), false);
		if (inventory == null) {
			return;
		}

		CloudItemStack caseStack = inventory.getItemStack(uuid);
		if (!(caseStack != null && caseStack.item instanceof CloudItemCase caseItem)) {
			return;
		}

		Optional<CloudItemStack> keyStack = inventory.getItemStacks(caseItem.getKeyItem()).findAny();
		if (keyStack.isEmpty()) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud("No case key found!", CloudConstants.CC_NAME, 0));
			return;
		}

		inventory.removeItemStack(caseStack.uuid, true);
		inventory.removeItemStack(keyStack.get().uuid, true);

		List<CloudItem> rouletteItems = caseItem.getRandomContentBulk(30);
		CloudItem result = rouletteItems.get(26);

		connectionHandler.sendPacket(new PacketInventoryCaseResults(rouletteItems));
		inventory.addItemStack(new CloudItemStack(result), true);
	}
}
