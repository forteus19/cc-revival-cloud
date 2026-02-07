package dev.vuis.cccr.cloud.main;

import dev.vuis.cccr.cloud.data.PlayerRank;

public final class RankExpMain {
	private RankExpMain() {
	}

	static void main() {
		int cumulative = 0;
		for (PlayerRank rank : PlayerRank.values()) {
			System.out.printf("%s %d%n", rank.getDisplayName(), cumulative += rank.getRequiredExp());
		}
	}
}
