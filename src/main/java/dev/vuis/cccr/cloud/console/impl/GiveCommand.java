package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;

public class GiveCommand implements Command {
	@Override
	public String getName() {
		return "give";
	}

	@Override
	public String getUsage() {
		return "<username> <item id | \"everything\"> <amount>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_GIVE;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 3) {
			source.sendMessage(getFullUsage());
			return;
		}
		String username = args[0];
		int amount = Integer.parseInt(args[2]);

		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(username);
		if (inventory == null) {
			source.sendMessage("Player %s does not have an inventory!", username);
			return;
		}

		switch (args[1]) {
			case "everything" -> {
				for (CloudItem item : CloudItems.all()) {
					if (!item.isDefault()) {
						inventory.addItemStack(new CloudItemStack(item), true);
					}
				}
			}
			default -> {
				int itemId = Integer.parseInt(args[1]);
				CloudItem item = CloudItems.getById(itemId);
				if (item == null) {
					source.sendMessage("Unknown item id '%d'.", itemId);
					return;
				}

				for (int i = 0; i < amount; i++) {
					inventory.addItemStack(new CloudItemStack(item), true);
				}
			}
		}
	}
}
