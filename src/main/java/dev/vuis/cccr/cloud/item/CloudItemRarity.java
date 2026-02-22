package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.util.RandomCollection;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CloudItemRarity {
	DEFAULT(0, "DEFAULT", "§7"),
	COAL(60, "TIER1", "§3"),
	IRON(40, "TIER2", "§b"),
	LAPIS(20, "TIER3", "§5"),
	GOLD(6, "TIER4", "§d"),
	DIAMOND(2, "TIER5", "§c"),
	OBSIDIAN(1, "TIER6", "§6");

	private static final RandomCollection<CloudItemRarity> RANDOM_COLLECTION = new RandomCollection<>(values(), CloudItemRarity::getWeight);

	@Getter
	private final int weight;
	@Getter
	private final String legacyName;
	@Getter
	private final String formattingCode;

	public static CloudItemRarity getRandom() {
		return RANDOM_COLLECTION.getRandom();
	}

	public CloudItemRarity getPreviousRarity() {
		return switch (this) {
			case DEFAULT -> throw new NoSuchElementException();
			case COAL -> DEFAULT;
			case IRON -> COAL;
			case LAPIS -> IRON;
			case GOLD -> LAPIS;
			case DIAMOND -> GOLD;
			case OBSIDIAN -> DIAMOND;
		};
	}
}
