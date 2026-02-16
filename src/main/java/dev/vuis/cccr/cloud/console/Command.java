package dev.vuis.cccr.cloud.console;

import dev.vuis.cccr.cloud.data.Permission;

public interface Command {
	Permission getPermission();

	void run(String[] args, CommandSource source);
}
