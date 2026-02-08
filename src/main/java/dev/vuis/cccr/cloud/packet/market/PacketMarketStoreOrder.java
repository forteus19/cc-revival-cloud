package dev.vuis.cccr.cloud.packet.market;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.MarketStore;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedInventory;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PacketMarketStoreOrder extends G_TcpPacketCustomPayload {
	private final FDSTagCompound tag = new FDSTagCompound("order");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		tag.load(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		PlayerData data = DataMaps.getPlayerData(connectionHandler.getConnectionUsername(), false);
		if (data == null) {
			return;
		}
		PlayerInventory inventory = DataMaps.getPlayerInventory(connectionHandler.getConnectionUsername(), false);
		if (inventory == null) {
			return;
		}

		int dataEmeralds = data.emeralds.get();

		List<CloudItemStack> resultStacks = new ArrayList<>();
		int emeralds = 0;

		FDSTagCompound orderTag;
		for (int i = 0; (orderTag = tag.getTagCompound("orderItem" + i)) != null; i++) {
			MarketStore.Category category = MarketStore.CATEGORIES.get(orderTag.getInteger("cat"));
			MarketStore.Item item = category.items().get(orderTag.getInteger("package"));
			int amount = orderTag.getInteger("amount");

			emeralds += item.price() * amount;
			if (emeralds > dataEmeralds) {
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"You do not have enough emeralds!", CloudConstants.CC_NAME, 0
				));
				break;
			}

			for (int j = 0; j < amount; j++) {
				resultStacks.add(new CloudItemStack(item.item()));
			}
		}

		inventory.addItemStacks(resultStacks.stream());
		data.emeralds.addAndGet(-emeralds);

		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			String.format("Bought %d items for %d emeralds!", resultStacks.size(), emeralds), CloudConstants.CC_NAME, 0
		));
		PacketRequestedInventory.sendForPlayer(connectionHandler, connectionHandler.getConnectionUsername());
	}
}
