package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.util.RandomCollection;
import lombok.Getter;

public enum CloudItemRarity {
	DEFAULT(0),
	COAL(120),
	IRON(40),
	LAPIS(15),
	GOLD(6),
	DIAMOND(2),
	OBSIDIAN(1);

	private static final RandomCollection<CloudItemRarity> RANDOM_COLLECTION = new RandomCollection<>(values(), CloudItemRarity::getWeight);

	@Getter
	private final int weight;

	CloudItemRarity(int weight) {
		this.weight = weight;
	}

	public static CloudItemRarity getRandomWeighted() {
		return RANDOM_COLLECTION.getRandom();
	}
}
