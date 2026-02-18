package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;

public class StopCommand implements Command {
	@Override
	public String getName() {
		return "stop";
	}

	@Override
	public String getUsage() {
		return "(DANGEROUS!!)";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_STOP;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		Cloud.instance().getCloudManager().initiateShutdown();
	}
}
