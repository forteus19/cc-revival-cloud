package dev.vuis.cccr.cloud.item;

import lombok.Getter;

public abstract class SuffixedCloudItem extends CloudItem {
	@Getter
	private final String suffix;

	protected SuffixedCloudItem(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault, CloudItem defaultItem) {
		super(id, mcId, rarity, displayName, isDefault);
		suffix = displayName.replaceFirst("^" + defaultItem.getDisplayName(), "").trim();
	}
}
