package dev.vuis.cccr.cloud.data;

@SuppressWarnings("unused")
public final class Permissions {
	public static final Permission ALL = new Permission("*");

	public static final Permission CHAT_SPAM_BYPASS = new Permission("chat.spam.bypass");

	public static final Permission COMMAND_EMERALDS = new Permission("command.emeralds");
	public static final Permission COMMAND_GIVE = new Permission("command.give");
	public static final Permission COMMAND_LIST = new Permission("command.list");
	public static final Permission COMMAND_RANDOMDROP = new Permission("command.randomdrop");
	public static final Permission COMMAND_STOP = new Permission("command.stop");
	public static final Permission COMMAND_TELL = new Permission("command.tell");

	private Permissions() {
	}
}
