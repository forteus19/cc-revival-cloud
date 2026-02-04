package dev.vuis.cccr.cloud.util;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.CloudConstants;
import java.net.http.HttpClient;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class Util {
	public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
	public static final UUID NULL_UUID = new UUID(0, 0);

	private Util() {
	}

	public static String getEnv(String env, String def) {
		String value = System.getenv(env);
		if (value == null) {
			return def;
		} else {
			return value;
		}
	}

	public static int getEnvInt(String env, int def) {
		String value = System.getenv(env);
		if (value == null) {
			return def;
		} else {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return def;
			}
		}
	}

	public static <T> T getRandomFromCollection(Collection<T> collection) {
		if (collection.isEmpty()) {
			throw new IllegalArgumentException("Empty collection");
		}
		return collection.stream()
			.skip(ThreadLocalRandom.current().nextInt(collection.size()))
			.findFirst()
			.orElseThrow();
	}

	public static boolean kickIfServer(G_ITcpConnectionHandler connectionHandler) {
		if (!(connectionHandler instanceof G_CloudTcpConnectionHandler cloudHandler)) {
			return true;
		}
		if (!cloudHandler.channel.equals(CloudConstants.CHANNEL_SERVER)) {
			cloudHandler.kickClientConnection("Attempted to send server packet on player channel");
			return true;
		}
		return false;
	}

	public static String getUndashedUuid(UUID uuid) {
		return uuid.toString().replace("-", "");
	}

	public static UUID parseUndashedUuid(String uuidStr) {
		if (uuidStr.length() != 32) {
			throw new IllegalArgumentException("Invalid undashed UUID");
		}
		return new UUID(
			Long.parseUnsignedLong(uuidStr.substring(0, 16), 16),
			Long.parseUnsignedLong(uuidStr.substring(16, 32), 16)
		);
	}
}
