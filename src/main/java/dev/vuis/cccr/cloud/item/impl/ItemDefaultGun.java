package dev.vuis.cccr.cloud.item.impl;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemDefaultGun {
	private final Map<CloudItemGun, UUID> uuidMap = new HashMap<>();

	public UUID getStackUuid(CloudItemGun item) {
		return uuidMap.get(item);
	}

	public void setStackUuid(UUID uuid, CloudItemGun item) {
		uuidMap.put(item, uuid);
	}

	public void writeTag(FDSTagCompound tag) {
		tag.setString("cat", CloudItemCategory.GUN.getId());
		tag.setIntegerArrayList("gunids", uuidMap.keySet().stream().map(CloudItemGun::getMcId).collect(Collectors.toCollection(ArrayList::new)));
		tag.setStringArrayList("gunuuids", uuidMap.values().stream().map(UUID::toString).collect(Collectors.toCollection(ArrayList::new)));
	}
}
