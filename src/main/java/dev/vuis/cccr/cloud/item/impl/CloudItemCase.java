package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.util.RandomCollection;
import dev.vuis.cccr.cloud.util.Util;
import java.util.List;
import lombok.Getter;

public class CloudItemCase extends CloudItem {
	private static final Multiset<CloudItemCase> DROP_SET = HashMultiset.create();

	@Getter
	private final CloudItem keyItem;
	private final RandomCollection<CloudItem> contents = new RandomCollection<>();

	public CloudItemCase(int id, CloudItemRarity rarity, String suffix, CloudItem keyItem, boolean isDefault) {
		super(id, rarity, suffix, isDefault);
		this.keyItem = keyItem;

		if (!isDefault) {
			DROP_SET.add(this, rarity.getWeight());
		}
	}

	public static CloudItemCase getRandomForDrop() {
		return Util.getRandomFromMultiset(DROP_SET);
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

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.CASE;
	}

	@Override
	public boolean isTradable() {
		return true;
	}

	@Override
	public boolean isSellable() {
		return true;
	}

	@Override
	public boolean isShowcasable() {
		return false;
	}

	@Override
	public boolean isNamable() {
		return false;
	}

	@Override
	public boolean isContractable() {
		return false;
	}
}
