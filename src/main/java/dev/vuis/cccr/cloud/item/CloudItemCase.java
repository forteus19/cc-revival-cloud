package dev.vuis.cccr.cloud.item;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

public class CloudItemCase extends CloudItem {
	@Getter
	private final CloudItem keyItem;
	private final Set<Content> contents = new HashSet<>();
	private int totalWeight = 0;

	public CloudItemCase(int id, CloudItemRarity rarity, String suffix, CloudItem keyItem, boolean isDefault, boolean isTradable, boolean isSellable, boolean isShowcasable, boolean isNamable, boolean isContractable) {
		super(id, rarity, suffix, isDefault, isTradable, isSellable, isShowcasable, isNamable, isContractable);
		this.keyItem = keyItem;
	}

	public CloudItemCase addContent(CloudItem item, int weight) {
		if (weight < 1) {
			throw new IllegalArgumentException("weight");
		}
		contents.add(new Content(item, totalWeight, totalWeight + weight));
		totalWeight += weight;
		return this;
	}

	public CloudItem getRandomContent() {
		int threshold = ThreadLocalRandom.current().nextInt(totalWeight);
		for (Content content : contents) {
			if (threshold >= content.minThreshold() && threshold < content.maxThreshold()) {
				return content.item();
			}
		}
		throw new AssertionError();
	}

	private record Content(
		CloudItem item,
		int minThreshold,
		int maxThreshold
	) {
	}
}
