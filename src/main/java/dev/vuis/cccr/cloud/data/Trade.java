package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.util.Util;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class Trade {
	public final DirtyHandler dirty = new DirtyHandler();

	public final LocalDateTime start;
	public final UUID offererUuid;
	public final UUID returnerUuid;
	public final List<CloudItemStack> offerStacks;
	public final List<CloudItemStack> returnStacks;

	public Result result = Result.PENDING;
	public LocalDateTime end;

	public void finalize(Result result) {
		this.result = result;
		end = Util.utcNow();
	}

	public void writeTag(FDSTagCompound tag) {
		PlayerData offererData = DataHandler.instance().getPlayerData(offererUuid);
		PlayerData returnerData = DataHandler.instance().getPlayerData(returnerUuid);

		tag.setString("tr1", offererUuid.toString());
		tag.setString("tr1name", offererData != null ? offererData.username.get() : "Unknown");
		tag.setString("tr2", returnerUuid.toString());
		tag.setString("tr2name", returnerData != null ? returnerData.username.get() : "Unknown");

		tag.setInteger("status", result == Result.PENDING ? 1 : 2);
		tag.setInteger("closedstate", result.ordinal());

		tag.setString("dateOpened", CloudConstants.DATE_FORMAT.format(start));
		if (end != null) {
			tag.setString("dateClosed", CloudConstants.DATE_FORMAT.format(end));
		}

		tag.setInteger("tr1offersize", offerStacks.size());
		for (int i = 0; i < offerStacks.size(); i++) {
			CloudItemStack stack = offerStacks.get(i);
			FDSTagCompound stackTag = new FDSTagCompound("tr1stack" + i);
			stack.writeTag(stackTag);
			tag.setTagCompound(stackTag.getTag(), stackTag);
		}

		tag.setInteger("tr2offersize", returnStacks.size());
		for (int i = 0; i < returnStacks.size(); i++) {
			CloudItemStack stack = returnStacks.get(i);
			FDSTagCompound stackTag = new FDSTagCompound("tr2stack" + i);
			stack.writeTag(stackTag);
			tag.setTagCompound(stackTag.getTag(), stackTag);
		}
	}

	public enum Result {
		PENDING,
		ACCEPTED,
		DENIED,
		ERROR
	}
}
