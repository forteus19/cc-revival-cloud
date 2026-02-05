package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;

public class CloudItemCape extends CloudItem {
	public CloudItemCape(int id, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, rarity, suffix, isDefault);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.CAPE;
	}

	@Override
	public boolean isTradable() {
		return false;
	}

	@Override
	public boolean isSellable() {
		return false;
	}

	@Override
	public boolean isShowcasable() {
		return true;
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
