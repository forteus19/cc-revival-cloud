package dev.vuis.cccr.cloud;

import java.time.format.DateTimeFormatter;

public final class CloudConstants {
	public static final String VERSION = "1.0.0-ALPHA-RC8";
	public static final String CC_NAME = "CounterCraft";
	public static final String CHANNEL_PLAYER = "player";
	public static final String CHANNEL_SERVER = "server";

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

	private CloudConstants() {
	}
}
