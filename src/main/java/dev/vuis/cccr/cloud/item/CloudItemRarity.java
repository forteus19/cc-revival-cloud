package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.util.RandomCollection;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CloudItemRarity {
	DEFAULT(0, 0),
	COAL(1, 120),
	IRON(2, 40),
	LAPIS(3, 15),
	GOLD(4, 6),
	DIAMOND(5, 2),
	OBSIDIAN(6, 1);

	private static final RandomCollection<CloudItemRarity> RANDOM_COLLECTION = new RandomCollection<>(values(), CloudItemRarity::getWeight);

	@Getter
	private final int index;
	@Getter
	private final int weight;

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
