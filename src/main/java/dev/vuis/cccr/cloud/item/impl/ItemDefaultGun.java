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
	private final Map<Integer, UUID> mcIdMap = new HashMap<>();

	@Override
	public UUID getStackUuid(CloudItem item) {
		return mcIdMap.get(item.getMcId());
	}

	@Override
	public void setStackUuid(UUID uuid, CloudItem item) {
		mcIdMap.put(item.getMcId(), uuid);
	}

	@Override
	public boolean clearStackUuid(CloudItem item) {
		mcIdMap.remove(item.getMcId());
		return false;
	}

	@Override
	public void writeTag(FDSTagCompound tag) {
		tag.setString("cat", CloudItemCategory.GUN.getId());

		ArrayList<Integer> ids = new ArrayList<>(mcIdMap.size());
		ArrayList<String> uuids = new ArrayList<>(mcIdMap.size());
		for (Map.Entry<Integer, UUID> entry : mcIdMap.entrySet()) {
			ids.add(entry.getKey());
			uuids.add(entry.getValue().toString());
		}
		tag.setIntegerArrayList("gunids", ids);
		tag.setStringArrayList("gunuuids", uuids);
	}
}
