package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemRarity;

public class CloudItemHat extends CloudItem {
	public CloudItemHat(int id, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, rarity, suffix, isDefault);
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
