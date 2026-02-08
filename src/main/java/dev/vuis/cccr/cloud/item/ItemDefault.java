package dev.vuis.cccr.cloud.item;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.UUID;

public interface ItemDefault {
	UUID getStackUuid(CloudItem item);

	void setStackUuid(UUID uuid, CloudItem item);

	boolean clearStackUuid(CloudItem item);

	void writeTag(FDSTagCompound tag);
}
