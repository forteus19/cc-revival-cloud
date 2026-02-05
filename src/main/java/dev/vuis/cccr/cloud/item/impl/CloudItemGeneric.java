package dev.vuis.cccr.cloud.item.impl;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemRarity;

public class CloudItemGeneric extends CloudItem {
	private final boolean isTradable;
	private final boolean isSellable;
	private final boolean isShowcasable;
	private final boolean isNamable;
	private final boolean isContractable;

	public CloudItemGeneric(int id, CloudItemRarity rarity, String suffix, boolean isDefault, boolean isTradable, boolean isSellable, boolean isShowcasable, boolean isNamable, boolean isContractable) {
		super(id, rarity, suffix, isDefault);
		this.isTradable = isTradable;
		this.isSellable = isSellable;
		this.isShowcasable = isShowcasable;
		this.isNamable = isNamable;
		this.isContractable = isContractable;
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
