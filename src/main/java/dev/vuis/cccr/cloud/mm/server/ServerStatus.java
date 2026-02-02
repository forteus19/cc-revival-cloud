package dev.vuis.cccr.cloud.mm.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ServerStatus {
	private ServerStatus() {
	}

	public static final Map<String, Integer> PORT = new ConcurrentHashMap<>();
	public static final Map<String, String> REGION = new ConcurrentHashMap<>();
	public static final Map<String, GameStatus> GAME = new ConcurrentHashMap<>();

	public static void remove(String username) {
		PORT.remove(username);
		REGION.remove(username);
		GAME.remove(username);
	}
}
