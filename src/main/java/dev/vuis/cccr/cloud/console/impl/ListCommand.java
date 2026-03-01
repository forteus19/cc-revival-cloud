package dev.vuis.cccr.cloud.console.impl;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.google.common.collect.Iterators;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import java.util.Locale;

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
			source.sendMessage("===== %s =====", channel.toUpperCase(Locale.ROOT));
			Iterators.partition(
				network.getNetChannel(channel).stream()
					.map(G_CloudTcpConnectionHandler::getConnectionUsername)
					.iterator(),
				5
			).forEachRemaining(
				partition -> source.sendMessage(String.join(" ", partition))
			);
		}
	}
}
