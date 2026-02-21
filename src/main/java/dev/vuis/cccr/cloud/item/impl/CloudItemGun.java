package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.util.Util;
import java.util.Collections;
import java.util.List;

public class CloudItemGun extends CloudItem {
	private static final Multiset<CloudItemGun> DROP_SET = HashMultiset.create();
	private static final ListMultimap<CloudItemRarity, CloudItemGun> RARITY_MAP = ArrayListMultimap.create();

	public CloudItemGun(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, mcId, rarity, suffix, isDefault);

		if (!isDefault) {
			DROP_SET.add(this, rarity.getWeight());
			RARITY_MAP.put(rarity, this);
		}
	}

	public static CloudItemGun getRandomForDrop() {
		return Util.getRandomFromMultiset(DROP_SET);
	}

	public static List<CloudItemGun> getByRarity(CloudItemRarity rarity) {
		return Collections.unmodifiableList(Multimaps.asMap(RARITY_MAP).get(rarity));
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
