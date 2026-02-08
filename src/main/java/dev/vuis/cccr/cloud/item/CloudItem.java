package dev.vuis.cccr.cloud.item;

import lombok.Getter;

@Getter
public abstract class CloudItem {
	private final int id;
	private final int mcId;
	private final CloudItemRarity rarity;
	private final String displayName;
	private final boolean isDefault;

	protected CloudItem(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault) {
		if (CloudItems.ID_MAP.containsKey(id)) {
			throw new IllegalStateException("Duplicate CloudItem id " + id + "!");
		}

		this.id = id;
		this.mcId = mcId;
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
