package dev.vuis.cccr.cloud.data;

public interface IDataHolder {
	IPlayerData getPlayerData(String username, boolean createIfAbsent);
}
