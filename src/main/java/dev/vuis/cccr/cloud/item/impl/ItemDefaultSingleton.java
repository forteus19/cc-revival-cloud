package dev.vuis.cccr.cloud.item.impl;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.ItemDefault;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemDefaultSingleton implements ItemDefault {
	private final CloudItemCategory category;
	private UUID stackUuid;

	@Override
	public UUID getStackUuid(CloudItem item) {
		return stackUuid;
	}

	@Override
	public void setStackUuid(UUID uuid, CloudItem item) {
		stackUuid = uuid;
	}

	@Override
	public boolean clearStackUuid(CloudItem item) {
		stackUuid = null;
		return true;
	}

	@Override
	public void writeTag(FDSTagCompound tag) {
		tag.setString("cat", category.getId());
		tag.setString("uuid", stackUuid.toString());
	}
}
