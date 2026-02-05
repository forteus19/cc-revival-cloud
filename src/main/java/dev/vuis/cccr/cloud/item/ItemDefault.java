package dev.vuis.cccr.cloud.item;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.UUID;

public interface ItemDefault<I extends CloudItem> {
	UUID getStackUuid(I item);

	void setStackUuid(UUID uuid, I item);

	void writeTag(FDSTagCompound tag);
}
