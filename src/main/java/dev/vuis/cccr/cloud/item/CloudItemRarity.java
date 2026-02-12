package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.util.RandomCollection;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CloudItemRarity {
	DEFAULT(0, 0, "DEFAULT"),
	COAL(1, 60, "TIER1"),
	IRON(2, 40, "TIER2"),
	LAPIS(3, 20, "TIER3"),
	GOLD(4, 6, "TIER4"),
	DIAMOND(5, 2, "TIER5"),
	OBSIDIAN(6, 1, "TIER6");

	private static final RandomCollection<CloudItemRarity> RANDOM_COLLECTION = new RandomCollection<>(values(), CloudItemRarity::getWeight);

	@Getter
	private final int index;
	@Getter
	private final int weight;
	@Getter
	private final String legacyName;

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
