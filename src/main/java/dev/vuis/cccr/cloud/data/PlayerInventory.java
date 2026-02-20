package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.Iterators;
import com.google.common.collect.Streams;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.ItemDefault;
import dev.vuis.cccr.cloud.item.impl.ItemDefaultGun;
import dev.vuis.cccr.cloud.item.impl.ItemDefaultSingleton;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemAdded;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemRemoved;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public final class PlayerInventory {
	private static final int SPLIT_SIZE = 64;

	private final Map<UUID, CloudItemStack> stackMap = new ConcurrentHashMap<>();
	private final Map<CloudItemCategory, ItemDefault> defaultsMap = new ConcurrentHashMap<>();

	@Getter
	private final UUID ownerUuid;
	final AtomicReference<String> username;

	PlayerInventory(UUID ownerUuid, String username) {
		this.ownerUuid = ownerUuid;
		this.username = new AtomicReference<>(username);
	}

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

	public Collection<CloudItemStack> getItemStacks() {
		return Collections.unmodifiableCollection(stackMap.values());
	}

	public void addItemStack(CloudItemStack itemStack, boolean sendPacket) {
		// safeguard to prevent inventory from breaking
		if (itemStack.item == null) {
			return;
		}

		stackMap.put(itemStack.uuid, itemStack);
		if (sendPacket) {
			PacketInventoryItemAdded packet = new PacketInventoryItemAdded();
			itemStack.writeTag(packet.tag);

			Cloud.instance().sendPacketToPlayer(username.get(), packet);
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

			Cloud.instance().sendPacketToPlayer(username.get(), packet);
		}
	}

	public boolean hasDefaults(CloudItemCategory category) {
		return defaultsMap.containsKey(category);
	}

	public CloudItemStack getDefaultStack(CloudItemCategory category, CloudItem item) {
		ItemDefault itemDef = defaultsMap.get(category);
		if (itemDef == null) {
			return null;
		}
		UUID defUuid = itemDef.getStackUuid(item);
		if (defUuid == null) {
			return null;
		}
		return getItemStack(defUuid);
	}

	public boolean isDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return false;
		}

		ItemDefault itemDef = defaultsMap.get(stack.item.getCategory());
		if (itemDef == null) {
			return false;
		}
		UUID defUuid = itemDef.getStackUuid(stack.item);
		return defUuid != null && defUuid.equals(uuid);
	}

	public void setDefaultStack(UUID uuid) {
		CloudItemStack stack = getEquippableItemStack(uuid);
		if (stack == null) {
			return;
		}

		defaultsMap.computeIfAbsent(
			stack.item.getCategory(),
			k -> k == CloudItemCategory.GUN ? new ItemDefaultGun() : new ItemDefaultSingleton(k)
		).setStackUuid(uuid, stack.item);
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
