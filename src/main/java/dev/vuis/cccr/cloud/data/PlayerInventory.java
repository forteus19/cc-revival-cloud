package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.Iterators;
import com.google.common.collect.Streams;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.impl.CloudItemGun;
import dev.vuis.cccr.cloud.item.impl.ItemDefaultGun;
import dev.vuis.cccr.cloud.item.impl.ItemDefaultSingleton;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemAdded;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemRemoved;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PlayerInventory {
	private static final int SPLIT_SIZE = 64;

	private final Map<UUID, CloudItemStack> stackMap = new ConcurrentHashMap<>();
	private final Map<CloudItemCategory, ItemDefaultSingleton> singletonDefaults = new ConcurrentHashMap<>();
	private final ItemDefaultGun gunDefaults = new ItemDefaultGun();

	private final String username;

	public CloudItemStack getItemStack(UUID uuid) {
		return stackMap.get(uuid);
	}

	private CloudItemStack getEquippableItemStack(UUID uuid) {
		CloudItemStack stack = getItemStack(uuid);
		return (stack != null && stack.item.isEquippable()) ? stack : null;
	}

	public Stream<CloudItemStack> getItemStacks(CloudItem item) {
		return stackMap.values().stream().filter(stack -> stack.item == item);
	}

	public void addItemStack(CloudItemStack itemStack, boolean sendPacket) {
		stackMap.put(itemStack.uuid, itemStack);
		if (sendPacket) {
			PacketInventoryItemAdded packet = new PacketInventoryItemAdded();
			itemStack.writeTag(packet.tag);

			Cloud.getInstance().sendPacketToPlayer(username, packet);
		}
	}

	public void addItemStacks(Stream<CloudItemStack> itemStacks) {
		stackMap.putAll(itemStacks.collect(Collectors.toMap(stack -> stack.uuid, stack -> stack)));
	}

	public void removeItemStack(UUID uuid, boolean sendPacket) {
		CloudItemStack previous = stackMap.remove(uuid);
		if (sendPacket && previous != null) {
			PacketInventoryItemRemoved packet = new PacketInventoryItemRemoved();
			previous.writeTag(packet.tag);

			Cloud.getInstance().sendPacketToPlayer(username, packet);
		}
	}

	public boolean hasDefaults(CloudItemCategory category) {
		if (category == CloudItemCategory.GUN) {
			return true;
		} else {
			return singletonDefaults.containsKey(category);
		}
	}

	public boolean hasDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return false;
		}

		if (stack.item instanceof CloudItemGun gunItem) {
			return gunDefaults.getStackUuid(gunItem) != null;
		} else {
			return singletonDefaults.containsKey(stack.item.getCategory());
		}
	}

	public void setDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return;
		}

		if (stack.item instanceof CloudItemGun gunItem) {
			gunDefaults.setStackUuid(uuid, gunItem);
		} else {
			singletonDefaults.computeIfAbsent(stack.item.getCategory(), ItemDefaultSingleton::new).setStackUuid(uuid);
		}
	}

	public CloudItemCategory clearDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return null;
		}

		CloudItemCategory category = stack.item.getCategory();

		if (stack.item instanceof CloudItemGun gunItem) {
			gunDefaults.clearStackUuid(gunItem);
		} else {
			singletonDefaults.remove(category);
		}

		return category;
	}

	public List<FDSTagCompound> writeStacksTagList() {
		return Streams.stream(Iterators.partition(
			stackMap.values().stream()
				.sorted(Comparator.comparingInt(stack -> stack.item.getId()))
				.iterator(),
			SPLIT_SIZE
		)).map(splitStacks -> {
			FDSTagCompound tag = new FDSTagCompound("invSplit");
			tag.setInteger("splitSize", splitStacks.size());

			for (int s = 0; s < splitStacks.size(); s++) {
				String tagName = "stackSplit" + s;

				FDSTagCompound stackTag = new FDSTagCompound(tagName);
				splitStacks.get(s).writeTag(stackTag);

				tag.setTagCompound(tagName, stackTag);
			}

			return tag;
		}).toList();
	}

	public void writeDefaultsTag(FDSTagCompound tag, CloudItemCategory category) {
		if (category == CloudItemCategory.GUN) {
			gunDefaults.writeTag(tag);
		} else {
			ItemDefaultSingleton defaultSingleton = singletonDefaults.get(category);
			if (defaultSingleton == null) {
				return;
			}
			defaultSingleton.writeTag(tag);
		}
	}
}
