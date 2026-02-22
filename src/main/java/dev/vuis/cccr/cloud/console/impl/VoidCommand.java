package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.data.PlayerData;

public class VoidCommand implements Command {
	@Override
	public String getName() {
		return "void";
	}

	@Override
	public String getUsage() {
		return "<username>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_GROUP;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 1) {
			source.sendMessage(getFullUsage());
			return;
		}
		String username = args[0];

		PlayerData playerData = DataHandler.instance().getPlayerData(username);
		if (playerData == null) {
			source.sendMessage("Player %s does not have data!", username);
			return;
		}

		playerData.punishmentsDirty.mark();
		playerData.punishments.clear();
		source.sendMessage("Cleared all punishments from %s", username);
	}
}
