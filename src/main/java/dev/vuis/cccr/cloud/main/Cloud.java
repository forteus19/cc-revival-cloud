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
	}

	@Override
	public void handleCommand(String command) {
		if (command.equals("stop")) {
			cloudManager.initiateShutdown();
		}
	}

	@Override
	public void getChannels(ArrayList<String> target) {
		target.add("player");
		target.add("server");
	}
}
