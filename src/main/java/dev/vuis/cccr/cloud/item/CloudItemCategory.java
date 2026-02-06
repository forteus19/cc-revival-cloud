package dev.vuis.cccr.cloud.item;

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

	@Getter
	private final String id;
}
