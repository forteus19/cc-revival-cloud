package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.market.MarketCategory;

public class CloudItemHat extends CloudItem {
	public CloudItemHat(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, mcId, rarity, suffix, isDefault);
		initFinished();
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.HAT;
	}

	@Override
	public MarketCategory getMarketCategory() {
		return null;
	}

	@Override
	public boolean isTradable() {
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

	@Override
	public boolean isSellable() {
		return false;
	}
}
