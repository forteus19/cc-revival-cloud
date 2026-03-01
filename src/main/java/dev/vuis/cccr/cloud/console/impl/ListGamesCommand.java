package dev.vuis.cccr.cloud.console.impl;

import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.Permissions;
import dev.vuis.cccr.cloud.mm.server.GameStatus;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import java.util.Map;

public class ListGamesCommand implements Command {
	@Override
	public String getName() {
		return "listgames";
	}

	@Override
	public String getUsage() {
		return null;
	}

	@Override
	public Permission getPermission() {
		return Permissions.COMMAND_LISTGAMES;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		for (Map.Entry<String, GameStatus> entry : ServerStatus.GAME.entrySet()) {
			GameStatus status = entry.getValue();
			source.sendMessage(
				"%s: %s %s %d/%d %s",
				entry.getKey(),
				status.gameType,
				status.mapType,
				status.numPlayers,
				status.numPlayers + status.remainingPlayerSlots,
				status.acceptingPlayers ? "OPEN" : "CLOSED"
			);
		}
	}
}
