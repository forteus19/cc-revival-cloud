package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.util.RandomCollection;
import java.util.List;
import lombok.Getter;

public class CloudItemCase extends CloudItem {
	@Getter
	private final CloudItem keyItem;
	private final RandomCollection<CloudItem> contents = new RandomCollection<>();

	public CloudItemCase(int id, CloudItemRarity rarity, String suffix, CloudItem keyItem, boolean isDefault, boolean isTradable, boolean isSellable, boolean isShowcasable, boolean isNamable, boolean isContractable) {
		super(id, rarity, suffix, isDefault, isTradable, isSellable, isShowcasable, isNamable, isContractable);
		this.keyItem = keyItem;
	}

	public CloudItemCase addContent(CloudItem item, int weight) {
		contents.add(item, weight);
		return this;
	}

	public CloudItem getRandomContent() {
		return contents.getRandom();
	}

	public List<CloudItem> getRandomContentBulk(int count) {
		return contents.getRandomBulk(count);
	}
}
