package dev.vuis.cccr.cloud.util;

import com.google.common.collect.Multiset;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Function;

public final class Util {
	private Util() {
	}

	public static <T, R> R ifNonNull(T t, Function<T, R> func) {
		return t != null ? func.apply(t) : null;
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

	public static int randomInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public static boolean randomBool() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	public static <T> T collectionRandom(Collection<T> collection) {
		if (collection.isEmpty()) {
			throw new IllegalArgumentException("Empty collection");
		}
		return collection.stream()
			.skip(randomInt(collection.size()))
			.findFirst()
			.orElseThrow();
	}

	public static <T> T listRandom(List<T> list) {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("Empty list");
		}
		return list.get(randomInt(list.size()));
	}

	public static <T> T multisetRandom(Multiset<T> multiset) {
		if (multiset.isEmpty()) {
			throw new IllegalArgumentException("Empty multiset");
		}

		int r = randomInt(multiset.size());
		int c = 0;

		for (Multiset.Entry<T> entry : multiset.entrySet()) {
			if (r < (c += entry.getCount())) {
				return entry.getElement();
			}
		}

		throw new AssertionError();
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

	public static boolean isHttpSuccess(int statusCode) {
		return statusCode >= 200 && statusCode < 300;
	}

	public static LocalDateTime utcNow() {
		return LocalDateTime.now(ZoneOffset.UTC);
	}
}
