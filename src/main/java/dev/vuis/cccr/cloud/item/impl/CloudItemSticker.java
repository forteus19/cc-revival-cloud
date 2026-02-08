package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;

public class CloudItemSticker extends CloudItem {
	public CloudItemSticker(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, mcId, rarity, suffix, isDefault);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.STICKER;
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
