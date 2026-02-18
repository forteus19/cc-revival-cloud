package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.data.PlayerData;

public class EmeraldsCommand implements Command {
	@Override
	public String getName() {
		return "emeralds";
	}

	@Override
	public String getUsage() {
		return "<username> <amount>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_EMERALDS;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 2) {
			source.send(getFullUsage());
			return;
		}
		String username = args[0];
		int amount = Integer.parseInt(args[1]);

		PlayerData playerData = DataHandler.instance().getPlayerData(username);
		if (playerData == null) {
			source.send("Player %s does not have data!", username);
			return;
		}

		playerData.dirty.mark();
		playerData.emeralds.addAndGet(amount);
	}
}
