package dev.vuis.cccr.cloud.item;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CloudItemStack {
	@Getter
	public final UUID uuid;
	public final CloudItem item;
	public String nametag = null;
	public final String[] stickers = new String[3];

	public CloudItemStack(CloudItem item) {
		this(UUID.randomUUID(), item);
	}

	public void writeTag(FDSTagCompound tag) {
		tag.setString("uuid", uuid.toString());
		tag.setInteger("itemid", item.getId());

		FDSTagCompound metadata = new FDSTagCompound("metadata");
		if (nametag != null) {
			metadata.setString("nametag", nametag);
		}
		for (int i = 0; i < stickers.length; i++) {
			if (stickers[i] != null) {
				metadata.setString("sticker" + i, stickers[i]);
			}
		}
		if (metadata.getTagsSize() > 0) {
			tag.setTagCompound("metadata", metadata);
		}
	}
}
