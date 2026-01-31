package dev.vuis.cccr.cloud.main;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.IDataHolder;
import dev.vuis.cccr.cloud.data.IPlayerData;
import dev.vuis.cccr.cloud.data.InMemoryDataHolder;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.util.ReflectUtil;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.Getter;

public final class Cloud implements ICloud {
	private static Cloud instance = null;

	{
		if (instance != null) {
			throw new IllegalStateException("Attempted to initialize cloud twice");
		}
		instance = this;
	}

	private final int port;

	@Getter
	private final G_CloudManager cloudManager;
	@Getter
	private final IDataHolder dataHolder = new InMemoryDataHolder();

	public Cloud(int port) {
		this.port = port;
		cloudManager = new G_CloudManager(this);
	}

	public static Cloud getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Not initialized yet");
		}
		return instance;
	}

	void onUpdate() {
		cloudManager.onUpdate();
	}

	void onShutdown() {
		cloudManager.log("Shutting down...");
	}

	@Override
	public String getCloudUsername() {
		return "CLOUD_CCRevival";
	}

	@Override
	public String getCloudVersion() {
		return "DEV";
	}

	@Override
	public int getCloudPort() {
		return port;
	}

	@Override
	public void getDisplayInformation(ArrayList<String> lines) {
	}

	@Override
	public boolean onConnectionReached(G_CloudTcpConnectionHandler connectionHandler) {
		cloudManager.log("Client \"" + connectionHandler.getConnectionUsername() + "\" is attemping connection");
		return true;
	}

	@Override
	public boolean onConnectionAddedToThread(G_CloudTcpConnectionHandler connectionHandler) {
		cloudManager.log("Client \"" + connectionHandler.getConnectionUsername() + "\" added to thread");
		return true;
	}

	@Override
	public void onConnectionEstablished(G_CloudTcpConnectionHandler connectionHandler, FDSTagCompound loginData) {
		String username = connectionHandler.getConnectionUsername();

		cloudManager.log("Client \"" + username + "\" established connection");
		cloudManager.log("Login info: " + loginData);

		IPlayerData playerData = dataHolder.getPlayerData(username, true);
		playerData.setOnline(true);

		PacketRequestedPlayerData packet = new PacketRequestedPlayerData();
		packet.username = username;
		playerData.putTag(packet.tag);
		connectionHandler.sendPacket(packet);

		for (G_CloudTcpConnectionHandler onlineConnection : cloudManager.getNetwork().getNetChannel("player")) {
			if (onlineConnection.isLoginSuccessful && !username.equals(onlineConnection.getConnectionUsername())) {
				onlineConnection.sendPacket(new PacketNotificationFromCloud("CounterCraft", username + " has logged in.", 0));
			}
		}
	}

	@Override
	public void onConnectionDisconnected(G_CloudTcpConnectionHandler connectionHandler) {
		String username = connectionHandler.getConnectionUsername();

		cloudManager.log("Client \"" + username + "\" disconnected (reason: \"" + ReflectUtil.getTerminationReason(connectionHandler.tcpConnection) + "\")");

		IPlayerData playerData = dataHolder.getPlayerData(username, true);
		playerData.setOnline(false);

		for (G_CloudTcpConnectionHandler onlineConnection : cloudManager.getNetwork().getNetChannel("player")) {
			if (onlineConnection.isLoginSuccessful && !username.equals(onlineConnection.getConnectionUsername())) {
				onlineConnection.sendPacket(new PacketNotificationFromCloud("CounterCraft", username + " has logged out.", 0));
			}
		}
	}

	@Override
	public void handleCommand(String command) {
		String[] parts = command.split(" ");
		boolean success = true;

		switch (parts[0]) {
			case "stop" -> {
				cloudManager.initiateShutdown();
			}
			case "tell" -> {
				if (parts.length < 3) {
					cloudManager.log("tell - Missing args: <username> <message>");
					break;
				}
				String username = parts[1];
				String message = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));

				cloudManager.getNetwork().sendPacketToClient("player", username, new PacketNotificationFromCloud("Cloud", message, 0));
			}
			default -> {
				cloudManager.log("Unknown command \"" + parts[0] + "\".");
				success = false;
			}
		}

		if (success) {
			cloudManager.log("Command: " + command);
		}
	}

	@Override
	public void getChannels(ArrayList<String> target) {
		target.add("player");
		target.add("server");
	}
}
