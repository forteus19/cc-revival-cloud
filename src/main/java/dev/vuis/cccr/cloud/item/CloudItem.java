package dev.vuis.cccr.cloud.item;

import dev.vuis.cccr.cloud.market.MarketCategory;
import dev.vuis.cccr.cloud.market.SuffixMap;
import java.util.HashMap;
import lombok.Getter;

public abstract class CloudItem {
	@Getter
	private final int id;
	@Getter
	private final int mcId;
	@Getter
	private final CloudItemRarity rarity;
	@Getter
	private final String displayName;
	@Getter
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
	}

	protected void initFinished() {
		if (isDefault) {
			CloudItems.DEFAULTS.add(this);
		} else if (isSellable()) {
			addToSuffixMap(
				CloudItems.MARKET_MAP
					.computeIfAbsent(getMarketCategory(), _ -> new HashMap<>())
					.computeIfAbsent(getPrefix(), _ -> SuffixMap.createForItem(this))
			);
		}
		CloudItems.ID_MAP.put(id, this);
		CloudItems.RARITY_MAP.put(rarity, this);
	}

	public abstract CloudItemCategory getCategory();

	public abstract MarketCategory getMarketCategory();

	public abstract boolean isTradable();

	public abstract boolean isShowcasable();

	public abstract boolean isNamable();

	public abstract boolean isContractable();

	public String getPrefix() {
		return displayName;
	}

	public boolean isSellable() {
		return !isDefault;
	}

	public boolean isEquippable() {
		return getCategory().getId() != null;
	}

	public void addToSuffixMap(SuffixMap map) {
		map.put(null, this);
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
