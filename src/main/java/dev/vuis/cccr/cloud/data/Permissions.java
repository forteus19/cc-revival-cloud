package dev.vuis.cccr.cloud.data;

@SuppressWarnings("unused")
public final class Permissions {
	public static final Permission ALL = new Permission(0, "*");

	public static final Permission CHAT_SPAM_BYPASS = new Permission(1, "chat.spam.bypass");

	public static final Permission COMMAND_EMERALDS = new Permission(2, "command.emeralds");
	public static final Permission COMMAND_GIVE = new Permission(3, "command.give");

	private Permissions() {
	}
}
