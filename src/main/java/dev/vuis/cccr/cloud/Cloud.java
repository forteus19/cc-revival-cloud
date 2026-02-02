package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.IDataHolder;
import dev.vuis.cccr.cloud.data.IPlayerData;
import dev.vuis.cccr.cloud.data.memory.InMemoryDataHolder;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
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
	private final String mmPassword;
	public final String mcHost;

	@Getter
	private final G_CloudManager cloudManager;
	@Getter
	private final IDataHolder dataHolder = new InMemoryDataHolder();

	public Cloud(int port, String mmPassword, String mcHost) {
		this.port = port;
		this.mmPassword = mmPassword;
		this.mcHost = mcHost;
		this.cloudManager = new G_CloudManager(this);
	}

	public static Cloud getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Not initialized yet");
		}
		return instance;
	}

	public void onUpdate() {
		cloudManager.onUpdate();
		SearchHandler.onUpdate();
	}

	public void onShutdown() {
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

		switch (connectionHandler.channel) {
			case CloudConstants.CHANNEL_PLAYER -> {
				IPlayerData playerData = dataHolder.getPlayerData(username, true);
				playerData.setOnline(true);

				PacketRequestedPlayerData packet = new PacketRequestedPlayerData();
				packet.username = username;
				playerData.putDataTag(packet.tag);
				connectionHandler.sendPacket(packet);

				connectionHandler.sendPacket(new PacketNotificationFromCloud("Cloud connection established!", CloudConstants.CC_NAME, 0));
				for (String user : playerData.getFriends()) {
					cloudManager.getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, user, new PacketNotificationFromCloud(username + " has logged in.", CloudConstants.CC_NAME, 0));
				}
			}
			case CloudConstants.CHANNEL_SERVER -> {
				ServerStatus.PORT.put(connectionHandler.getConnectionUsername(), loginData.getInteger("port"));
			}
		}
	}

	@Override
	public void onConnectionDisconnected(G_CloudTcpConnectionHandler connectionHandler) {
		String username = connectionHandler.getConnectionUsername();

		cloudManager.log("Client \"" + username + "\" disconnected (reason: \"" + ReflectUtil.getTerminationReason(connectionHandler.tcpConnection) + "\")");

		switch (connectionHandler.channel) {
			case CloudConstants.CHANNEL_PLAYER -> {
				IPlayerData playerData = dataHolder.getPlayerData(username, true);
				playerData.setOnline(false);

				for (String user : playerData.getFriends()) {
					cloudManager.getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, user, new PacketNotificationFromCloud(username + " has logged out.", CloudConstants.CC_NAME, 0));
				}
			}
			case CloudConstants.CHANNEL_SERVER -> {
				ServerStatus.remove(username);
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

				cloudManager.getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, username, new PacketNotificationFromCloud(message, "Cloud", 0));
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
		target.add(CloudConstants.CHANNEL_PLAYER);
		target.add(CloudConstants.CHANNEL_SERVER);
	}

	public boolean checkMatchMakingPassword(String password) {
		return mmPassword == null || mmPassword.equals(password);
	}
}
