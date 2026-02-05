package dev.vuis.cccr.cloud.item;

import lombok.Getter;

public abstract class CloudItem {
	@Getter
	private final int id;
	@Getter
	private final CloudItemRarity rarity;
	@Getter
	private final String displayName;
	@Getter
	private final boolean isDefault;

	protected CloudItem(int id, CloudItemRarity rarity, String displayName, boolean isDefault) {
		if (CloudItems.ID_MAP.containsKey(id)) {
			throw new IllegalStateException("Duplicate CloudItem id " + id + "!");
		}

		this.id = id;
		this.rarity = rarity;
		this.displayName = displayName;
		this.isDefault = isDefault;

		if (isDefault) {
			CloudItems.DEFAULTS.add(this);
		}
		CloudItems.ID_MAP.put(id, this);
		CloudItems.RARITY_MAP.put(rarity, this);
	}

	public abstract CloudItemCategory getCategory();

	public abstract boolean isTradable();

	public abstract boolean isSellable();

	public abstract boolean isShowcasable();

	public abstract boolean isNamable();

	public abstract boolean isContractable();

	public boolean isEquippable() {
		return getCategory().getId() != null;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CloudItem item && id == item.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}
