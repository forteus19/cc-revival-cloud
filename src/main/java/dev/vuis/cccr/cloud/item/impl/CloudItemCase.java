package dev.vuis.cccr.cloud.item.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;
import dev.vuis.cccr.cloud.util.RandomCollection;
import dev.vuis.cccr.cloud.util.Util;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import lombok.Getter;

public class CloudItemCase extends CloudItem {
	private static final Multiset<CloudItemCase> DROP_SET = HashMultiset.create();

	@Getter
	private final CloudItem keyItem;
	private final List<CloudItem> specialItemPool;

	public final RandomCollection<CloudItem> contents = new RandomCollection<>();

	public CloudItemCase(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault, CloudItem keyItem, List<CloudItem> specialItemPool) {
		super(id, mcId, rarity, suffix, isDefault);
		this.keyItem = keyItem;
		this.specialItemPool = specialItemPool;

		if (!isDefault) {
			DROP_SET.add(this, rarity.getWeight());
		}
	}

	public static CloudItemCase getRandomForDrop() {
		return Util.getRandomFromMultiset(DROP_SET);
	}

	public CloudItem getRandomSpecialDrop() {
		return specialItemPool.get(ThreadLocalRandom.current().nextInt(specialItemPool.size()));
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
	public boolean isTradable() {
		return true;
	}

	@Override
	public boolean isSellable() {
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
