package dev.vuis.cccr.cloud.market;

import java.util.Locale;

public enum MarketCategory {
	RIFLE,
	SMG,
	HEAVY,
	SNIPER,
	PISTOL,
	KNIFE,
	CASE,
	KEY,
	STICKER,
	MISC;

	public static MarketCategory fromFilter(String filter) {
		return switch (filter.toLowerCase(Locale.ROOT)) {
			case "rifle" -> RIFLE;
			case "smg" -> SMG;
			case "heavy" -> HEAVY;
			case "sniper" -> SNIPER;
			case "pistol" -> PISTOL;
			case "knife" -> KNIFE;
			case "case" -> CASE;
			case "key" -> KEY;
			case "sticker" -> STICKER;
			case "misc" -> MISC;
			default -> null;
		};
	}
}
