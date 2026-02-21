package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCollection;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemGun;
import dev.vuis.cccr.cloud.util.PacketUtil;
import dev.vuis.cccr.cloud.util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class PacketInventoryContract extends G_TcpPacketCustomPayload {
	private static final Map<CloudItemRarity, ListMultimap<CloudItemCollection, CloudItemGun>> RARITY_POOL = new HashMap<>();

	static {
		for (CloudItem item : CloudItems.all()) {
			if (item instanceof CloudItemGun gunItem) {
				RARITY_POOL.computeIfAbsent(item.getRarity(), _ -> ArrayListMultimap.create()).put(gunItem.getCollection(), gunItem);
			}
		}
	}

	private final List<UUID> stackUuids = new ArrayList<>();

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		int numStacks = in.readInt();
		in.readInt();

		for (int i = 0; i < numStacks; i++) {
			stackUuids.add(UUID.fromString(readString(in)));
		}
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

		if (stackUuids.size() != 10) {
			connectionHandler.sendPacket(invalidNotification());
			return;
		}

		CloudItemRarity rarity = null;
		List<CloudItemGun> items = new ArrayList<>(10);
		for (UUID stackUuid : stackUuids) {
			CloudItemStack stack = inventory.getItemStack(stackUuid);
			if (stack == null) {
				connectionHandler.sendPacket(invalidNotification());
				return;
			}
			if (!(stack.item instanceof CloudItemGun gunItem)) {
				connectionHandler.sendPacket(invalidNotification());
				return;
			}

			if (rarity == null) {
				rarity = stack.item.getRarity();
			} else if (stack.item.getRarity() != rarity) {
				connectionHandler.sendPacket(invalidNotification());
				return;
			}
			items.add(gunItem);
		}

		ListMultimap<CloudItemCollection, CloudItemGun> pool = RARITY_POOL.get(CloudItemRarity.values()[rarity.ordinal() + 1]);
		if (pool == null) {
			connectionHandler.sendPacket(invalidNotification());
			return;
		}

		Multiset<CloudItemCollection> collections = HashMultiset.create();
		for (CloudItemGun item : items) {
			CloudItemCollection collection = item.getCollection();
			if (!pool.get(collection).isEmpty()) {
				collections.add(collection);
			}
		}
		if (collections.isEmpty()) {
			connectionHandler.sendPacket(new PacketNotificationFromCloud(
				"A suitable trade up item could not be found!", CloudConstants.CC_NAME, 0
			));
			return;
		}

		List<CloudItemGun> target = pool.get(Util.getRandomFromMultiset(collections));

		for (UUID stackUuid : stackUuids) {
			inventory.removeItemStack(stackUuid, true);
		}
		inventory.addItemStack(new CloudItemStack(target.get(ThreadLocalRandom.current().nextInt(target.size()))), true);
	}

	private static PacketNotificationFromCloud invalidNotification() {
		return new PacketNotificationFromCloud(
			"Invalid trade up!", CloudConstants.CC_NAME, 0
		);
	}
}
