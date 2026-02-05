package dev.vuis.cccr.cloud.item;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CloudItemCategory {
	BOOSTER("booster"),
	CALLING_CARD("card"),
	CAPE("cape"),
	CASE(null),
	COIN("coin"),
	GENERIC(null),
	GUN("gun"),
	HAT("hat"),
	KEY("key"),
	KNIFE("knife"),
	STICKER("sticker"),
	STICKER_CAPSULE(null);

	private static final Map<String, CloudItemCategory> ID_MAP = Arrays.stream(CloudItemCategory.values())
		.collect(Collectors.toUnmodifiableMap(CloudItemCategory::getId, Function.identity()));

	@Getter
	private final String id;

	public static CloudItemCategory getById(String id) {
		return ID_MAP.get(id);
	}
}
