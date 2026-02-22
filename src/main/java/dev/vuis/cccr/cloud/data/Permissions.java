package dev.vuis.cccr.cloud.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public final class Permissions {
	static final Set<Permission> INSTANCES = new HashSet<>();

	public static final Permission ALL = new Permission("*");

	public static final Permission CHAT_SPAM_BYPASS = new Permission("chat.spam.bypass");

	public static final Permission COMMAND_EMERALDS = new Permission("command.emeralds");
	public static final Permission COMMAND_EXP = new Permission("command.exp");
	public static final Permission COMMAND_GIVE = new Permission("command.give");
	public static final Permission COMMAND_GROUP = new Permission("command.group");
	public static final Permission COMMAND_LIST = new Permission("command.list");
	public static final Permission COMMAND_PUNISH = new Permission("command.punish");
	public static final Permission COMMAND_RANDOMDROP = new Permission("command.randomdrop");
	public static final Permission COMMAND_STOP = new Permission("command.stop");
	public static final Permission COMMAND_TELL = new Permission("command.tell");
	public static final Permission COMMAND_VOID = new Permission("command.void");

	private Permissions() {
	}

	public static Set<Permission> allWithout(Permission... excluded) {
		Set<Permission> instancesCopy = new HashSet<>(INSTANCES);
		instancesCopy.removeAll(Set.of(excluded));
		return Collections.unmodifiableSet(instancesCopy);
	}
}
