package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.util.Util;

public class CloudItemGun extends CloudItem {
	private static final Multiset<CloudItemGun> DROP_SET = HashMultiset.create();

	public CloudItemGun(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, mcId, rarity, suffix, isDefault);

		if (!isDefault) {
			DROP_SET.add(this, rarity.getWeight());
		}
	}

	public static CloudItemGun getRandomForDrop() {
		return Util.getRandomFromMultiset(DROP_SET);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.GUN;
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
		return true;
	}

	@Override
	public boolean isNamable() {
		return true;
	}

	@Override
	public boolean isContractable() {
		return true;
	}
}
