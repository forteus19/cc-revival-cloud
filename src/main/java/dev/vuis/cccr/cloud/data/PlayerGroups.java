package dev.vuis.cccr.cloud.data;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public final class PlayerGroups {
	static final Map<String, PlayerGroup> TAG_MAP = new HashMap<>();

	public static final PlayerGroup DEV = new PlayerGroup(
		"Developer", "DEV", "§e", Set.of(
			Permissions.ALL
		));

	private PlayerGroups() {
	}

	public static PlayerGroup getByTag(String tag) {
		return TAG_MAP.get(tag.toUpperCase(Locale.ROOT));
	}
}
