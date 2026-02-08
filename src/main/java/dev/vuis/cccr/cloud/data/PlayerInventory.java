package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.Iterators;
import com.google.common.collect.Streams;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.ItemDefault;
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
	private final Map<CloudItemCategory, ItemDefault> defaultsMap = new ConcurrentHashMap<>();

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
			return defaultsMap.containsKey(category);
		}
	}

	public boolean hasDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return false;
		}

		ItemDefault itemDef = defaultsMap.get(stack.item.getCategory());
		return itemDef != null && itemDef.getStackUuid(stack.item) != null;
	}

	public void setDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return;
		}

		defaultsMap.computeIfAbsent(stack.item.getCategory(), ItemDefaultSingleton::new).setStackUuid(uuid, stack.item);
	}

	public CloudItemCategory clearDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return null;
		}

		CloudItemCategory category = stack.item.getCategory();

		ItemDefault itemDef = defaultsMap.get(category);
		if (itemDef != null) {
			if (itemDef.clearStackUuid(stack.item)) {
				defaultsMap.remove(category);
			}
			return category;
		} else {
			return null;
		}
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
		ItemDefault itemDef = defaultsMap.get(category);
		if (itemDef != null) {
			itemDef.writeTag(tag);
		}
	}
}
