package dev.vuis.cccr.cloud.mm.server;

import java.util.ArrayList;
import java.util.List;

public final class GameStatus {
	public String gameType;
	public String mapType;
	public int remainingPlayerSlots;
	public int remainingSpectatorSlots;
	public int numPlayers;
	public boolean acceptingPlayers;
	public final List<Team> teams = new ArrayList<>();

	public record Team(
		String name,
		int players
	) {
	}
}
