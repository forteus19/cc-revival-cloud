package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;

public class CloudItemKnife extends CloudItem {
	public CloudItemKnife(int id, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, rarity, suffix, isDefault);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.KNIFE;
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
