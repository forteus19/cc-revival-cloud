package dev.vuis.cccr.cloud.console;

import dev.vuis.cccr.cloud.console.impl.EmeraldsCommand;
import java.util.Arrays;
import java.util.Map;

public final class Commands {
	private static final Map<String, Command> REGISTRY = Map.ofEntries(
		Map.entry("emeralds", new EmeraldsCommand())
	);

	private Commands() {
	}

	public static void execute(String commandStr, CommandSource source) {
		String[] parts = commandStr.split(" ");
		if (parts.length < 1) {
			return;
		}

		Command command = REGISTRY.get(parts[0]);
		if (command == null) {
			source.send("Unknown command: %s", parts[0]);
			return;
		}
		if (!source.hasPermission(command.getPermission())) {
			source.send("You are not allowed to run this command!");
			return;
		}

		try {
			command.run(Arrays.copyOfRange(parts, 1, parts.length), source);
		} catch (Exception e) {
			e.printStackTrace();
			source.send("An error occured while executing the command");
		}
	}
}
