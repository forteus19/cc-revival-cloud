package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.market.MarketCategory;

public class CloudItemGeneric extends CloudItem {
	private final boolean isTradable;
	private final boolean isSellable;
	private final boolean isShowcasable;
	private final boolean isNamable;
	private final boolean isContractable;

	public CloudItemGeneric(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault, boolean isTradable, boolean isSellable, boolean isShowcasable, boolean isNamable, boolean isContractable) {
		super(id, mcId, rarity, suffix, isDefault);
		this.isTradable = isTradable;
		this.isSellable = isSellable;
		this.isShowcasable = isShowcasable;
		this.isNamable = isNamable;
		this.isContractable = isContractable;
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.GENERIC;
	}

	@Override
	public MarketCategory getMarketCategory() {
		return MarketCategory.MISC;
	}

	@Override
	public boolean isTradable() {
		return isTradable;
	}

	@Override
	public boolean isSellable() {
		return isSellable;
	}

	@Override
	public boolean isShowcasable() {
		return isShowcasable;
	}

	@Override
	public boolean isNamable() {
		return isNamable;
	}

	@Override
	public boolean isContractable() {
		return isContractable;
	}
}
