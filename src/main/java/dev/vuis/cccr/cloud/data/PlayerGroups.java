package dev.vuis.cccr.cloud.data;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public final class PlayerGroups {
	static final Map<String, PlayerGroup> TAG_MAP = new HashMap<>();

	public static final PlayerGroup ADMIN = new PlayerGroup("Admin", "ADMIN", "§e", Permissions.allWithout(
		Permissions.COMMAND_GROUP
	));
	public static final PlayerGroup DEVELOPER = new PlayerGroup("Developer", "DEV", "§e", Set.of(
		Permissions.ALL
	));
	public static final PlayerGroup MODERATOR = new PlayerGroup("Moderator", "MOD", "§e", Set.of(
		Permissions.CHAT_SPAM_BYPASS,
		Permissions.COMMAND_LIST,
		Permissions.COMMAND_PUNISH,
		Permissions.COMMAND_VOID
	));
	public static final PlayerGroup HELPER = new PlayerGroup("Helper", "HLPR", "§6", Set.of(
		Permissions.COMMAND_PUNISH
	));

	private PlayerGroups() {
	}

	public static PlayerGroup getByTag(String tag) {
		return TAG_MAP.get(tag.toUpperCase(Locale.ROOT));
	}
}
