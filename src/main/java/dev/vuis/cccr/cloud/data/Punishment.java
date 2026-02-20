package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.CloudConstants;
import java.time.LocalDateTime;
import java.util.UUID;

public record Punishment(
	UUID uuid,
	String issuer,
	PunishmentType type,
	String reason,
	LocalDateTime start,
	LocalDateTime end
) {
	public void writeTag(FDSTagCompound tag, LocalDateTime now) {
		tag.setString("id", uuid.toString());
		tag.setString("reason", reason);
		tag.setString("datecurrent", CloudConstants.DATE_FORMAT.format(now));
		tag.setString("dateend", CloudConstants.DATE_FORMAT.format(end));
		tag.setBoolean("closed", false);
		tag.setString("type", type.toString());
	}
}
