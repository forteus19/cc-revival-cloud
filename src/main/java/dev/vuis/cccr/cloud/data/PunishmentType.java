package dev.vuis.cccr.cloud.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PunishmentType {
	MUTE(false),
	WARNING(false),
	BAN_COMP(true),
	BAN_MM(true),
	BAN_TRADING(false),
	BAN_CLOUD(true);

	@Getter
	private final boolean sendBannedPacket;

	public static PunishmentType getById(String id) {
		return switch (id) {
			case "mute" -> MUTE;
			case "warn" -> WARNING;
			case "comp" -> BAN_COMP;
			case "mm" -> BAN_MM;
			case "trade" -> BAN_TRADING;
			case "cloud" -> BAN_CLOUD;
			default -> null;
		};
	}
}
