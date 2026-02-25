package dev.vuis.cccr.cloud.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CloudItemCollection {
	NONE(null),
	ALPHA_1("Alpha"),
	ALPHA_2("Alpha2"),
	ALPHA_3("Alpha3"),
	HALLOWEEN("Halloween1"),
	EXO("Exo"),
	PREALPHA("Prealpha"),
	BLAZE("Blaze");

	@Getter
	private final String name;
}
