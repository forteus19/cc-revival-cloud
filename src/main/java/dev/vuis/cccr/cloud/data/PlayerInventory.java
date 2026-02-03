package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemAdded;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PlayerInventory {
	private static final int SPLIT_SIZE = 64;

	private final List<CloudItemStack> itemStacks = Collections.synchronizedList(new ArrayList<>());

	private final String username;

	public List<FDSTagCompound> writeTagList() {
		int splits = Math.ceilDiv(itemStacks.size(), SPLIT_SIZE);
		List<FDSTagCompound> tags = new ArrayList<>(splits);

		for (int i = 0; i < splits; i++) {
			int start = i * SPLIT_SIZE;
			int splitSize = Math.min(SPLIT_SIZE, itemStacks.size() - start);

			FDSTagCompound tag = new FDSTagCompound("invSplit");
			tag.setInteger("splitSize", splitSize);

			for (int j = 0; j < splitSize; j++) {
				String tagName = "stackSplit" + j;

				FDSTagCompound stackTag = new FDSTagCompound(tagName);
				itemStacks.get(start + j).writeTag(stackTag);

				tag.setTagCompound(tagName, stackTag);
			}

			tags.add(tag);
		}

		return tags;
	}

	public void addItemStack(CloudItemStack itemStack, boolean sendPacket) {
		itemStacks.add(itemStack);
		if (sendPacket) {
			PacketInventoryItemAdded packet = new PacketInventoryItemAdded();
			itemStack.writeTag(packet.tag);

			Cloud.getInstance().getCloudManager().getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, username, packet);
		}
	}
}
