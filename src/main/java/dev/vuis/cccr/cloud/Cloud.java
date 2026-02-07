package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_TcpPacket;
import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedInventory;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.util.ReflectUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
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
	private final CloudAuth auth = new CloudAuth(this);

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

	public static Cloud instanceLog(String message) {
		Cloud instance = getInstance();
		instance.cloudManager.log(message);
		return instance;
	}

	public static Cloud instanceLog(String format, Object... args) {
		return instanceLog(String.format(format, args));
	}

	public Cloud log(String message) {
		cloudManager.log(message);
		return this;
	}

	public Cloud log(String format, Object... args) {
		return log(String.format(format, args));
	}

	public void onUpdate() {
		cloudManager.onUpdate();
		SearchHandler.onUpdate();
	}

	public void onShutdown() {
		log("Shutting down...");
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
		log("Client \"%s\" is attemping connection", connectionHandler.getConnectionUsername());
		return true;
	}

	@Override
	public boolean onConnectionAddedToThread(G_CloudTcpConnectionHandler connectionHandler) {
		log("Client \"%s\" added to thread", connectionHandler.getConnectionUsername());
		return true;
	}

	@Override
	public void onConnectionEstablished(G_CloudTcpConnectionHandler connectionHandler, FDSTagCompound loginData) {
		String username = connectionHandler.getConnectionUsername();

		log("Client \"%s\" sent login", username);
//		log("Login info: %s", loginData);

		switch (connectionHandler.channel) {
			case CloudConstants.CHANNEL_PLAYER -> {
				String key = loginData.getString("key");
				if (key == null) {
					connectionHandler.kickClientConnection("Missing mod key");
					return;
				}
				auth.submitAuthenticationJob(username, key);
			}
			case CloudConstants.CHANNEL_SERVER -> {
				ServerStatus.PORT.put(connectionHandler.getConnectionUsername(), loginData.getInteger("port"));
			}
		}
	}

	void onConnectionAuthenticationSuccess(G_CloudTcpConnectionHandler connectionHandler, UUID resultUuid) {
		String username = connectionHandler.getConnectionUsername();

		log("Client \"%s\" authenticated", username);

		PlayerData playerData = DataMaps.getPlayerData(username, true);
		playerData.uuid.set(resultUuid);
		playerData.online.set(true);

		DataMaps.getPlayerInventory(username, true);

		PacketRequestedPlayerData.sendForPlayer(connectionHandler, username);
		PacketRequestedInventory.sendForPlayer(connectionHandler, username);

		connectionHandler.sendPacket(new PacketNotificationFromCloud("Cloud connection established!", CloudConstants.CC_NAME, 0));
		for (String user : playerData.friends) {
			sendPacketToPlayer(user, new PacketNotificationFromCloud(username + " is online!", CloudConstants.CC_NAME, 0));
		}
	}

	void onConnectionAuthenticationFailure(G_CloudTcpConnectionHandler connectionHandler) {
		String username = connectionHandler.getConnectionUsername();

		log("Client \"%s\" failed authentication", username);
		connectionHandler.kickClientConnection("Authentication failed");
	}

	@Override
	public void onConnectionDisconnected(G_CloudTcpConnectionHandler connectionHandler) {
		String username = connectionHandler.getConnectionUsername();

		log("Client \"%s\" disconnected (reason: %s)", username, ReflectUtil.getTerminationReason(connectionHandler.tcpConnection));

		switch (connectionHandler.channel) {
			case CloudConstants.CHANNEL_PLAYER -> {
				auth.onClientDisconnected(username);

				PlayerData playerData = DataMaps.getPlayerData(username, false);
				if (playerData != null) {
					playerData.online.set(false);

					for (String user : playerData.friends) {
						sendPacketToPlayer(user, new PacketNotificationFromCloud(username + " has logged out.", CloudConstants.CC_NAME, 0));
					}
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
			case "give" -> {
				if (parts.length < 3) {
					log("give - Missing args: <username> <id>");
					break;
				}
				String username = parts[1];
				CloudItem item = CloudItems.getById(Integer.parseInt(parts[2]));

				PlayerInventory inventory = DataMaps.getPlayerInventory(username, false);
				if (inventory == null) {
					log("randomdrop - Player %s does not have an inventory!", username);
					break;
				}

				inventory.addItemStack(new CloudItemStack(item), true);
			}
			case "randomdrop" -> {
				if (parts.length < 3) {
					log("randomdrop - Missing args: <username> <count>");
					break;
				}
				String username = parts[1];
				int count = Integer.parseInt(parts[2]);

				PlayerInventory inventory = DataMaps.getPlayerInventory(username, false);
				if (inventory == null) {
					log("randomdrop - Player %s does not have an inventory!", username);
					break;
				}

				for (int i = 0; i < count; i++) {
					inventory.addItemStack(new CloudItemStack(CloudItems.getRandomDrop()), true);
				}
			}
			case "stop" -> {
				cloudManager.initiateShutdown();
			}
			case "tell" -> {
				if (parts.length < 3) {
					log("tell - Missing args: <username> <message>");
					break;
				}
				String username = parts[1];
				String message = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));

				sendPacketToPlayer(username, new PacketNotificationFromCloud(message, "Cloud", 0));
			}
			default -> {
				log("Unknown command \"%s\"", parts[0]);
				success = false;
			}
		}

		if (success) {
			log("Command: %s", command);
		}
	}

	@Override
	public void getChannels(ArrayList<String> target) {
		target.add(CloudConstants.CHANNEL_PLAYER);
		target.add(CloudConstants.CHANNEL_SERVER);
	}

	public G_CloudTcpConnectionHandler getPlayerConnection(String username) {
		return cloudManager.getNetwork().getClientConnectionHandler(CloudConstants.CHANNEL_PLAYER, username);
	}

	public Cloud sendPacketToPlayer(String username, G_TcpPacket packet) {
		cloudManager.getNetwork().sendPacketToClient(CloudConstants.CHANNEL_PLAYER, username, packet);
		return this;
	}

	public boolean checkMatchMakingPassword(String password) {
		return mmPassword == null || mmPassword.equals(password);
	}
}
