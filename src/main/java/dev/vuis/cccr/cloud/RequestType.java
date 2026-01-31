package dev.vuis.cccr.cloud;

public enum RequestType {
	PLAYER_DATA("status"),
	PLAYER_INVENTORY("inventory"),
	PLAYER_INVENTORY_DEFAULTS("itemdefaultall"),
	PLAYER_TRADES("trade"),
	PLAYER_FRIENDS("friends"),
	PLAYER_PUNISHMENTS("punishments"),
	NEWS("news"),
	CLOUDSTATS("cloudstats"),
	MARKETVALUE("marketvalue"),
	MYLISTINGS("mylistings"),
	STOREITEMS("storeitems");

	private final String type;

	RequestType(String par1) {
		this.type = par1;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
