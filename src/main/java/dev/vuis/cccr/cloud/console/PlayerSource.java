package dev.vuis.cccr.cloud.console;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.packet.PacketConsoleMessage;
import lombok.Getter;

public class PlayerSource implements CommandSource {
	private final G_ITcpConnectionHandler connectionHandler;
	@Getter
	private final PlayerData playerData;

	public PlayerSource(G_ITcpConnectionHandler connectionHandler) {
		this.connectionHandler = connectionHandler;
		PlayerData playerData = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (playerData == null) {
			throw new IllegalStateException("No player data for " + connectionHandler.getConnectionUsername());
		}
		this.playerData = playerData;
	}

	@Override
	public String getName() {
		return connectionHandler.getConnectionUsername();
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return playerData.hasPermission(permission);
	}

	@Override
	public void sendMessage(String message) {
		connectionHandler.sendPacket(new PacketConsoleMessage(message));
	}
}
