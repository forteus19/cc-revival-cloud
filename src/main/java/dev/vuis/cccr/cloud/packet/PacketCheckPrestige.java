package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.data.PlayerRank;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketCheckPrestige extends G_TcpPacketCustomPayload {
	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
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

		if (data.exp.get() < PlayerRank.PRESTIGE_EXP) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"You do not have enough EXP to prestige!", CloudConstants.CC_NAME, 0
			));
			return;
		}

		data.dirty.mark();
		data.exp.addAndGet(-PlayerRank.PRESTIGE_EXP);
		int newPrestige = data.prestige.incrementAndGet();

		if (newPrestige == 1) {
			inventory.addItemStack(new CloudItemStack(CloudItems.COIN_PRESTIGE), true);
		}
//		inventory.addItemStack(new CloudItemStack(CloudItems.BOOSTER_EXP_2), true);
//		inventory.addItemStack(new CloudItemStack(CloudItems.BOOSTER_EXP_2), true);

		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			String.format("You have prestiged to level %d!", newPrestige), CloudConstants.CC_NAME, 0
		));
	}
}
