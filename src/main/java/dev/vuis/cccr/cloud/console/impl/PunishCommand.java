package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.Punishment;
import dev.vuis.cccr.cloud.data.PunishmentType;
import dev.vuis.cccr.cloud.packet.PacketBanned;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.UUID;

public class PunishCommand implements Command {
	@Override
	public String getName() {
		return "punish";
	}

	@Override
	public String getUsage() {
		return "<username> <type> <minutes> <reason>";
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_PUNISH;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (args.length < 4) {
			source.sendMessage(getFullUsage());
			return;
		}
		PunishmentType type = PunishmentType.getById(args[1]);
		if (type == null) {
			source.sendMessage("Unknown type '%s'.", args[1]);
			return;
		}
		String username = args[0];
		long minutes = Long.parseLong(args[2]);
		String reason = String.join(" ", Arrays.copyOfRange(args, 3, args.length));

		PlayerData playerData = DataHandler.instance().getPlayerData(username);
		if (playerData == null) {
			source.sendMessage("Player %s does not have data!", username);
			return;
		}

		LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
		playerData.punishmentsDirty.mark();
		playerData.punishments.add(new Punishment(UUID.randomUUID(), source.getName(), type, reason, now, now.plusMinutes(minutes)));
		if (type.isSendBannedPacket()) {
			source.sendPacket(new PacketBanned());
		}
	}
}
