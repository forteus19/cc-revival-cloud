package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemCollection;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.item.SuffixedCloudItem;
import dev.vuis.cccr.cloud.market.MarketCategory;
import dev.vuis.cccr.cloud.util.Util;
import lombok.Getter;

public class CloudItemGun extends SuffixedCloudItem {
	private static final Multiset<CloudItemGun> DROP_SET = HashMultiset.create();

	@Getter
	private final CloudItemCollection collection;
	private final MarketCategory marketCategory;

	private CloudItemGun(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault, CloudItem defaultItem, CloudItemCollection collection, MarketCategory marketCategory) {
		super(id, mcId, rarity, displayName, isDefault, defaultItem);
		this.collection = collection;
		this.marketCategory = marketCategory;

		if (!isDefault) {
			DROP_SET.add(this, rarity.getWeight());
		}
	}

	public CloudItemGun(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault, CloudItem defaultItem, CloudItemCollection collection) {
		this(id, mcId, rarity, displayName, isDefault, defaultItem, collection, defaultItem.getMarketCategory());
	}

	public CloudItemGun(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault, MarketCategory marketCategory) {
		this(id, mcId, rarity, displayName, isDefault, null, CloudItemCollection.NONE, marketCategory);
	}

	public static CloudItemGun getRandomForDrop() {
		return Util.getRandomFromMultiset(DROP_SET);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.GUN;
	}

	@Override
	public MarketCategory getMarketCategory() {
		return marketCategory;
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
