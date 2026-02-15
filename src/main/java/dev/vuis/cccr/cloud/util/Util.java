package dev.vuis.cccr.cloud.util;

import com.google.common.collect.Multiset;
import java.net.http.HttpClient;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

public final class Util {
	public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
	public static final UUID NULL_UUID = new UUID(0, 0);

	private Util() {
	}

	public static <T> void enumerate(Collection<T> collection, BiConsumer<T, Integer> elementConsumer) {
		int i = 0;
		for (T t : collection) {
			elementConsumer.accept(t, i++);
		}
	}

	public static String getEnv(String env, String def) {
		String value = System.getenv(env);
		if (value != null) {
			return value;
		} else {
			return def;
		}
	}

	public static String getEnvOrThrow(String env) {
		String value = System.getenv(env);
		if (value != null) {
			return value;
		} else {
			throw new IllegalStateException("Missing env: " + env);
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

	public static <T> T getRandomFromMultiset(Multiset<T> multiset) {
		if (multiset.isEmpty()) {
			throw new IllegalArgumentException("Empty multiset");
		}

		int r = ThreadLocalRandom.current().nextInt(multiset.size());
		int c = 0;

		for (Multiset.Entry<T> entry : multiset.entrySet()) {
			if (r < (c += entry.getCount())) {
				return entry.getElement();
			}
		}

		throw new AssertionError();
	}

	public static boolean isHttpSuccess(int statusCode) {
		return statusCode >= 200 && statusCode < 300;
	}
}
