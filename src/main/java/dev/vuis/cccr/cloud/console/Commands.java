package dev.vuis.cccr.cloud.console;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.console.impl.EmeraldsCommand;
import dev.vuis.cccr.cloud.console.impl.ExpCommand;
import dev.vuis.cccr.cloud.console.impl.GiveCommand;
import dev.vuis.cccr.cloud.console.impl.GroupCommand;
import dev.vuis.cccr.cloud.console.impl.HelpCommand;
import dev.vuis.cccr.cloud.console.impl.ListCommand;
import dev.vuis.cccr.cloud.console.impl.PunishCommand;
import dev.vuis.cccr.cloud.console.impl.RandomDropCommand;
import dev.vuis.cccr.cloud.console.impl.StopCommand;
import dev.vuis.cccr.cloud.console.impl.TellCommand;
import dev.vuis.cccr.cloud.console.impl.VoidCommand;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Commands {
	private static final Map<String, Command> REGISTRY = new HashMap<>();

	static {
		register(new EmeraldsCommand());
		register(new ExpCommand());
		register(new GiveCommand());
		register(new GroupCommand());
		register(new HelpCommand());
		register(new ListCommand());
		register(new PunishCommand());
		register(new RandomDropCommand());
		register(new StopCommand());
		register(new TellCommand());
		register(new VoidCommand());
	}

	private Commands() {
	}

	private static void register(Command command) {
		REGISTRY.put(command.getName(), command);
	}

	public static Collection<Command> getRegistered() {
		return Collections.unmodifiableCollection(REGISTRY.values());
	}

	public static void execute(String commandStr, CommandSource source) {
		String[] parts = commandStr.split(" ");
		if (parts.length < 1) {
			return;
		}

		Command command = REGISTRY.get(parts[0]);
		if (command == null) {
			source.sendMessage("Unknown command: %s", parts[0]);
			return;
		}
		if (!command.hasPermission(source)) {
			source.sendMessage("You are not allowed to run this command!");
			return;
		}

		try {
			command.run(Arrays.copyOfRange(parts, 1, parts.length), source);
		} catch (Exception e) {
			e.printStackTrace();
			source.sendMessage("An error occured while executing the command");
		}

		Cloud.instanceLog("%s ran command: %s", source.getName(), commandStr);
	}
}
