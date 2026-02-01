package dev.vuis.cccr.cloud.data.server;

import java.util.HashMap;
import java.util.Map;

public final class ServerStatus {
	private ServerStatus() {
	}

	public static final Map<String, Integer> PORT = new HashMap<>();
	public static final Map<String, String> REGION = new HashMap<>();
	public static final Map<String, GameStatus> GAME = new HashMap<>();

	public static void remove(String username) {
		PORT.remove(username);
		REGION.remove(username);
		GAME.remove(username);
	}
}
