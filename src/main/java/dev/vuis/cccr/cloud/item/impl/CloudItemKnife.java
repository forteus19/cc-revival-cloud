package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.item.SuffixedCloudItem;
import dev.vuis.cccr.cloud.market.MarketCategory;

public class CloudItemKnife extends SuffixedCloudItem {
	public CloudItemKnife(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault, CloudItem defaultItem) {
		super(id, mcId, rarity, displayName, isDefault, defaultItem);
	}

	public CloudItemKnife(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		this(id, mcId, rarity, suffix, isDefault, null);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.KNIFE;
	}

	@Override
	public MarketCategory getMarketCategory() {
		return MarketCategory.KNIFE;
	}

	@Override
	public boolean isTradable() {
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
