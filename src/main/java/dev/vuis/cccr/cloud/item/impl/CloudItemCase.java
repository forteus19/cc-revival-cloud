package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.market.MarketCategory;
import dev.vuis.cccr.cloud.util.RandomCollection;
import dev.vuis.cccr.cloud.util.Util;
import java.util.List;
import java.util.function.Consumer;
import lombok.Getter;

public class CloudItemCase extends CloudItem {
	private static final Multiset<CloudItemCase> DROP_SET = HashMultiset.create();

	@Getter
	private final CloudItem keyItem;
	private final List<CloudItem> specialItemPool;

	public final RandomCollection<CloudItem> contents = new RandomCollection<>();

	public CloudItemCase(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault, CloudItem keyItem, List<CloudItem> specialItemPool, boolean drop) {
		super(id, mcId, rarity, suffix, isDefault);
		this.keyItem = keyItem;
		this.specialItemPool = specialItemPool;

		if (drop) {
			DROP_SET.add(this, rarity.getWeight());
		}

		initFinished();
	}

	public static CloudItemCase getRandomForDrop() {
		return Util.multisetRandom(DROP_SET);
	}

	public CloudItem getRandomSpecialDrop() {
		return Util.listRandom(Util.randomInt(5) > 0 ? specialItemPool : CloudItems.POOL_KNIFE_VANILLA);
	}

	public CloudItemCase modifyContents(Consumer<RandomCollection<CloudItem>> collectionConsumer) {
		collectionConsumer.accept(contents);
		return this;
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.CASE;
	}

	@Override
	public MarketCategory getMarketCategory() {
		return MarketCategory.CASE;
	}

	@Override
	public boolean isTradable() {
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
