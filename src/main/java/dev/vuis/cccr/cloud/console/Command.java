package dev.vuis.cccr.cloud.console;

import dev.vuis.cccr.cloud.data.Permission;

public interface Command {
	String getName();

	String getUsage();

	default String getFullUsage() {
		String usage = getUsage();
		if (usage == null) {
			return "Usage: " + getName();
		} else {
			return "Usage: " + getName() + " " + usage;
		}
	}

	Permission getPermission();

	default boolean hasPermission(CommandSource source) {
		Permission permission = getPermission();
		return permission == null || source.hasPermission(permission);
	}

	void run(String[] args, CommandSource source);
}
