package dev.vuis.cccr.cloud.console;

import dev.vuis.cccr.cloud.data.Permission;

public interface CommandSource {
	String getName();

	boolean hasPermission(Permission permission);

	void sendMessage(String message);

	default void sendMessage(String fmt, Object... args) {
		sendMessage(String.format(fmt, args));
	}
}
