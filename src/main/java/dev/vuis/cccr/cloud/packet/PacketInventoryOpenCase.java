package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.achievement.CloudAchievements;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.util.PacketUtil;
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
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData data = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (data == null) {
			return;
		}
		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(connectionHandler.getConnectionUsername());
		if (inventory == null) {
			return;
		}

		CloudItemStack caseStack = inventory.getItemStack(uuid);
		if (!(caseStack != null && caseStack.item instanceof CloudItemCase caseItem)) {
			return;
		}

		Optional<CloudItemStack> keyStack = inventory.getItemStacks(caseItem.getKeyItem()).findAny();
		if (keyStack.isEmpty()) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				String.format("You need a '%s' key to open this!", caseItem.getKeyItem().getDisplayName()), CloudConstants.CC_NAME, 0
			));
			return;
		}

		inventory.removeItemStack(caseStack.uuid, true);
		inventory.removeItemStack(keyStack.get().uuid, true);

		CloudItem result = caseItem.contents.getRandom();
		if (result.getRarity() == CloudItemRarity.OBSIDIAN || result == CloudItems.KNIFE_TAC_DISPLAY) {
			CloudAchievements.ACH_USER_INV_OPEN_ULTRA_RARE.handleUnlock(data, null);
		}

		List<CloudItem> rouletteItems = caseItem.contents.getRandomBulk(30);
		rouletteItems.set(26, result);

		if (result == CloudItems.KNIFE_TAC_DISPLAY) {
			result = caseItem.getRandomSpecialDrop();
		}

		connectionHandler.sendPacket(new PacketInventoryCaseResults(rouletteItems));
		inventory.addItemStack(new CloudItemStack(result), true);

		CloudAchievements.ACH_USER_INV_OPEN_CASE.handleUnlock(data, null);
		inventory.incrementCaseResult(caseItem, result);
	}
}
