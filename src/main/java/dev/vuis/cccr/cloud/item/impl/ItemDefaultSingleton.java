package dev.vuis.cccr.cloud.item.impl;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class ItemDefaultSingleton {
	private final CloudItemCategory category;
	@Getter
	@Setter
	private UUID stackUuid;

	public void writeTag(FDSTagCompound tag) {
		tag.setString("cat", category.getId());
		tag.setString("uuid", stackUuid.toString());
	}
}
