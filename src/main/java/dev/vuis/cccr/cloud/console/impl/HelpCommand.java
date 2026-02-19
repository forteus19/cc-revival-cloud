package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.console.Commands;
import dev.vuis.cccr.cloud.data.Permission;
import java.util.Comparator;

public class HelpCommand implements Command {
	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getUsage() {
		return null;
	}

	@Override
	public Permission getPermission() {
		return null;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		Commands.getRegistered().stream()
			.filter(command -> command.hasPermission(source))
			.sorted(Comparator.comparing(Command::getName))
			.forEachOrdered(command -> {
				source.sendMessage(command.getFullUsage());
			});
	}
}
