package dev.vuis.cccr.cloud.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class PlayerGroups {
	static final Map<String, PlayerGroup> TAG_MAP = new HashMap<>();

	public static final PlayerGroup DEV = new PlayerGroup(
		"Developer", "DEV", "§e", Set.of(
			Permissions.ALL
		));

	private PlayerGroups() {
	}
}
