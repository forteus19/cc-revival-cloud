package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerGroup;
import dev.vuis.cccr.cloud.data.PlayerGroups;

public class GroupCommand implements Command {
	@Override
	public String getName() {
		return "group";
	}

	@Override
	public String getUsage() {
		return "<username> <tag>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_GROUP;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 2) {
			source.sendMessage(getFullUsage());
			return;
		}
		String username = args[0];
		String groupTag = args[1];

		PlayerData data = DataHandler.instance().getPlayerData(username);
		if (data == null) {
			source.sendMessage("Player %s does not have data!", username);
			return;
		}

		if (groupTag.equalsIgnoreCase("clear")) {
			data.dirty.mark();
			data.group.set(null);
			source.sendMessage("Cleared %s's group", username);
			return;
		}

		PlayerGroup group = PlayerGroups.getByTag(groupTag);
		if (group == null) {
			source.sendMessage("Invalid group tag '%s'.", args[1]);
			return;
		}

		data.dirty.mark();
		data.group.set(group);
		source.sendMessage("Set %s's group to %s", username, group.displayTag());
	}
}
