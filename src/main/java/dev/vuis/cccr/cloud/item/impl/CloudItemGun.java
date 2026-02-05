package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.util.Util;
import lombok.Getter;

public class CloudItemGun extends CloudItem {
	private static final Multiset<CloudItemGun> DROP_SET = HashMultiset.create();

	@Getter
	private final int mcId;

	public CloudItemGun(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, rarity, suffix, isDefault);
		this.mcId = mcId;

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
