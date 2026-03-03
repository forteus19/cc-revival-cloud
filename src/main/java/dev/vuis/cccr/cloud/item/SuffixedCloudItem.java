package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.market.SuffixMap;
import lombok.Getter;

public abstract class SuffixedCloudItem extends CloudItem {
	private final String prefix;
	@Getter
	private final String suffix;

	protected SuffixedCloudItem(int id, int mcId, CloudItemRarity rarity, String displayName, boolean isDefault, CloudItem defaultItem) {
		super(id, mcId, rarity, displayName, isDefault);
		prefix = defaultItem != null ? defaultItem.getDisplayName() : displayName;
		suffix = defaultItem != null ? displayName.replaceFirst("^" + defaultItem.getDisplayName(), "").trim() : null;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void addToSuffixMap(SuffixMap map) {
		if (suffix != null) {
			map.put(suffix, this);
		} else {
			super.addToSuffixMap(map);
		}
	}
}
