package dev.vuis.cccr.cloud.item.impl;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemDefaultGun {
	private final Map<CloudItemGun, UUID> uuidMap = new HashMap<>();

	public UUID getStackUuid(CloudItemGun item) {
		return uuidMap.get(item);
	}

	public void setStackUuid(UUID uuid, CloudItemGun item) {
		uuidMap.put(item, uuid);
	}

	public void clearStackUuid(CloudItemGun item) {
		uuidMap.remove(item);
	}

	public void writeTag(FDSTagCompound tag) {
		tag.setString("cat", CloudItemCategory.GUN.getId());

		ArrayList<Integer> ids = new ArrayList<>(uuidMap.size());
		ArrayList<String> uuids = new ArrayList<>(uuidMap.size());
		for (Map.Entry<CloudItemGun, UUID> entry : uuidMap.entrySet()) {
			ids.add(entry.getKey().getMcId());
			uuids.add(entry.getValue().toString());
		}
		tag.setIntegerArrayList("gunids", ids);
		tag.setStringArrayList("gunuuids", uuids);
	}
}
