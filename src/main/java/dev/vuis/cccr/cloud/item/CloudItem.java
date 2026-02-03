package dev.vuis.cccr.cloud.item;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class CloudItem {
	static final Map<Integer, CloudItem> ID_MAP = new HashMap<>();

	@Getter
	private final int id;
	@Getter
	private final CloudItemRarity rarity;
	@Getter
	private final String suffix;
	@Getter
	private final boolean isDefault;
	@Getter
	private final boolean isTradable;
	@Getter
	private final boolean isSellable;
	@Getter
	private final boolean isShowcasable;
	@Getter
	private final boolean isNamable;
	@Getter
	private final boolean isContractable;

	public CloudItem(int id, CloudItemRarity rarity, String suffix, boolean isDefault, boolean isTradable, boolean isSellable, boolean isShowcasable, boolean isNamable, boolean isContractable) {
		this.id = id;
		this.rarity = rarity;
		this.suffix = suffix;
		this.isDefault = isDefault;
		this.isTradable = isTradable;
		this.isSellable = isSellable;
		this.isShowcasable = isShowcasable;
		this.isNamable = isNamable;
		this.isContractable = isContractable;
		ID_MAP.put(id, this);
	}

	public static CloudItem getById(int id) {
		return ID_MAP.get(id);
	}
}
