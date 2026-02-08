package dev.vuis.cccr.cloud.item.impl;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.ItemDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemDefaultGun implements ItemDefault {
	private final Map<CloudItem, UUID> uuidMap = new HashMap<>();

	@Override
	public UUID getStackUuid(CloudItem item) {
		return uuidMap.get(item);
	}

	@Override
	public void setStackUuid(UUID uuid, CloudItem item) {
		uuidMap.put(item, uuid);
	}

	@Override
	public boolean clearStackUuid(CloudItem item) {
		uuidMap.remove(item);
		return false;
	}

	@Override
	public void writeTag(FDSTagCompound tag) {
		tag.setString("cat", CloudItemCategory.GUN.getId());

		ArrayList<Integer> ids = new ArrayList<>(uuidMap.size());
		ArrayList<String> uuids = new ArrayList<>(uuidMap.size());
		for (Map.Entry<CloudItem, UUID> entry : uuidMap.entrySet()) {
			ids.add(entry.getKey().getMcId());
			uuids.add(entry.getValue().toString());
		}
		tag.setIntegerArrayList("gunids", ids);
		tag.setStringArrayList("gunuuids", uuids);
	}
}
