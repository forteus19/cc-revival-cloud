package dev.vuis.cccr.cloud.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CloudItemCollection {
	ALPHA("Alpha"),
	BETA("Beta"),
	GAMMA("Gamma"),
	DELTA("Delta"),
	EPSILON("Epsilon"),
	CHRONICAL("Chronical"),
	MYTHIC("Mythic"),
	OASIS("Oasis");

	@Getter
	private final String name;
}
