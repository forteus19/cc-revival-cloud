package dev.vuis.cccr.cloud.console.impl;

import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import java.util.Arrays;

public class TellCommand implements Command {
	@Override
	public String getName() {
		return "tell";
	}

	@Override
	public String getUsage() {
		return "<username> <title> <message>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_TELL;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 3) {
			source.sendMessage(getFullUsage());
			return;
		}
		String username = args[0];
		String title = args[1];
		String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

		if (username.equals("@a")) {
			for (G_CloudTcpConnectionHandler connectionHandler : Cloud.instance().getCloudManager().getNetwork().getNetChannel(CloudConstants.CHANNEL_PLAYER)) {
				connectionHandler.sendPacket(new PacketNotificationFromCloud(message, title, 0));
			}
		} else {
			Cloud.instance().sendPacketToPlayer(username, new PacketNotificationFromCloud(message, title, 0));
		}
	}
}
