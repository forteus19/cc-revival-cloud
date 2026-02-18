package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;

public class RandomDropCommand implements Command {
	@Override
	public String getName() {
		return "randomdrop";
	}

	@Override
	public String getUsage() {
		return "<username> <amount>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_RANDOMDROP;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 2) {
			source.send(getFullUsage());
			return;
		}
		String username = args[0];
		int amount = Integer.parseInt(args[1]);

		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(username);
		if (inventory == null) {
			source.send("Player %s does not have an inventory!", username);
			return;
		}

		for (int i = 0; i < amount; i++) {
			inventory.addItemStack(new CloudItemStack(CloudItems.getRandomDrop()), true);
		}
	}
}
