package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import dev.vuis.cccr.cloud.item.CloudItemRarity;

public class CloudItemStickerCapsule extends CloudItem {
	public CloudItemStickerCapsule(int id, int mcId, CloudItemRarity rarity, String suffix, boolean isDefault) {
		super(id, mcId, rarity, suffix, isDefault);
	}

	@Override
	public CloudItemCategory getCategory() {
		return CloudItemCategory.STICKER_CAPSULE;
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
