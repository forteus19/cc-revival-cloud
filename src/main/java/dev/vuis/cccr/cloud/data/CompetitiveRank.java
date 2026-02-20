package dev.vuis.cccr.cloud.data;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CompetitiveRank {
	IRON_1(0),
	IRON_2(50),
	IRON_3(125),
	IRON_MASTER(225),
	GOLD_1(350),
	GOLD_2(500),
	GOLD_3(675),
	GOLD_MASTER(875),
	DIAMOND_1(1100),
	DIAMOND_2(1350),
	DIAMOND_3(1625),
	DIAMOND_MASTER(1925),
	OBSIDIAN_1(2250),
	OBSIDIAN_2(2600),
	OBSIDIAN_3(2975),
	OBSIDIAN_MASTER(3375),
	ENDER_ELITE(4000);

	private final int expThreshold;

	public static CompetitiveRank getRankFromExp(int exp) {
		CompetitiveRank lastRank = IRON_1;
		for (CompetitiveRank rank : values()) {
			if (exp >= rank.expThreshold) {
				lastRank = rank;
			} else {
				break;
			}
		}
		return lastRank;
	}

	public int getExpProgress(int exp) {
		return exp - expThreshold;
	}

	public int getNextRequiredExp() {
		if (this == ENDER_ELITE) {
			return 0;
		}
		return values()[ordinal() + 1].expThreshold - expThreshold;
	}
}
