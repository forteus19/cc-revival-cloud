package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record Punishment(
	UUID uuid,
	String issuer,
	PunishmentType type,
	String reason,
	LocalDateTime start,
	LocalDateTime end
) {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

	public void writeTag(FDSTagCompound tag) {
		LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
		tag.setString("id", uuid.toString());
		tag.setString("reason", reason);
		tag.setString("datecurrent", DATE_FORMAT.format(now));
		tag.setString("dateend", DATE_FORMAT.format(end));
		tag.setBoolean("closed", false);
		tag.setString("type", type.toString());
	}
}
