package dev.vuis.cccr.cloud.console.impl;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import java.util.stream.Collectors;

public class ListCommand implements Command {
	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String getUsage() {
		return null;
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_LIST;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		G_CloudNetworkManager network = Cloud.instance().getCloudManager().getNetwork();
		for (String channel : network.getChannels()) {
			source.sendMessage("%s: %s", channel, network.getNetChannel(channel).stream()
				.map(G_CloudTcpConnectionHandler::getConnectionUsername)
				.collect(Collectors.joining(", "))
			);
		}
	}
}
