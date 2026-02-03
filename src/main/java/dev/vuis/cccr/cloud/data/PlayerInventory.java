package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import com.google.common.collect.Iterators;
import com.google.common.collect.Streams;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemAdded;
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

	private final String username;

	public List<FDSTagCompound> writeTagList() {
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

	public void addItemStack(CloudItemStack itemStack, boolean sendPacket) {
		stackMap.put(itemStack.uuid, itemStack);
		if (sendPacket) {
			PacketInventoryItemAdded packet = new PacketInventoryItemAdded();
			itemStack.writeTag(packet.tag);

			Cloud.getInstance().getCloudManager().getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, username, packet);
		}
	}

	public void addItemStacks(Stream<CloudItemStack> itemStacks) {
		stackMap.putAll(itemStacks.collect(Collectors.toMap(stack -> stack.uuid, stack -> stack)));
	}
}
