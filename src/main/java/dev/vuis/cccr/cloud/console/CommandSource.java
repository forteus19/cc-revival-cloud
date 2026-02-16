package dev.vuis.cccr.cloud.console;

import dev.vuis.cccr.cloud.data.Permission;

public interface CommandSource {
	String getName();

	boolean hasPermission(Permission permission);

	void send(String message);

	default void send(String fmt, Object... args) {
		send(String.format(fmt, args));
	}
}
