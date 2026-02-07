package dev.vuis.cccr.cloud.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PlayerRank {
	CHICKEN("Chicken", 0),
	PIG("Pig", 1000),
	COW("Cow", 2000),
	HORSE("Horse", 3000),
	LLAMA("Llama", 4000),
	OCELOT("Ocelot", 6000),
	WOLF("Wolf", 7500),
	FOX("Fox", 9000),
	SPIDER("Spider", 10500),
	SKELETON("Skeleton", 12000),
	ZOMBIE("Zombie", 14000),
	CREEPER("Creeper", 16000),
	PILLAGER("Pillager", 18000),
	VINDICATOR("Vindicator", 20000),
	VEX("Vex", 22000),
	SHULKER("Shulker", 24000),
	BLAZE("Blaze", 26000),
	GHAST("Ghast", 28000),
	WITHER("Wither", 30000),
	DRAGON("Dragon", 40000);

	private final String displayName;
	private final int requiredExp;

	private static final int[] CUMULATIVE_EXP_VALUES;
	static {
		PlayerRank[] ranks = values();
		CUMULATIVE_EXP_VALUES = new int[ranks.length];

		int cumulative = 0;
		for (int i = 0; i < ranks.length; i++) {
			CUMULATIVE_EXP_VALUES[i] = (cumulative += ranks[i].requiredExp);
		}
	}

	public static PlayerRank getRankFromExp(int exp) {
		if (exp < 0) {
			throw new IllegalArgumentException("exp");
		}

		PlayerRank[] ranks = values();
		for (int i = ranks.length - 1; i >= 0; i--) {
			if (exp >= CUMULATIVE_EXP_VALUES[i]) {
				return ranks[i];
			}
		}
		throw new AssertionError();
	}
}
