package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_TcpPacket;
import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.console.Commands;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedInventory;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.util.ReflectUtil;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
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
	private final DataHandler dataHandler;
	@Getter
	private final CloudAuth auth = new CloudAuth(this);

	private final ScheduledExecutorService minuteExecutor = Executors.newSingleThreadScheduledExecutor();

	public Cloud(int port, String mmPassword, String mcHost, DataSource dataSource) {
		this.port = port;
		this.mmPassword = mmPassword;
		this.mcHost = mcHost;
		this.cloudManager = new G_CloudManager(this);
		this.dataHandler = new DataHandler(dataSource);
	}

	public static Cloud instance() {
		if (instance == null) {
			throw new IllegalStateException("Not initialized yet");
		}
		return instance;
	}

	public static Cloud instanceLog(String message) {
		Cloud instance = instance();
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

	public void onStartup() {
		minuteExecutor.scheduleAtFixedRate(this::onMinute, 1, 1, TimeUnit.MINUTES);
	}

	public void onShutdown() {
		log("Shutting down...");
		minuteExecutor.shutdown();
		dataHandler.saveCachedData();
	}

	public void onUpdate() {
		cloudManager.onUpdate();
		SearchHandler.onUpdate();
	}

	private void onMinute() {
		for (PlayerData data : dataHandler.getCachedPlayerData()) {
			if (data.online.get()) {
				data.minutesPlayed.incrementAndGet();
				data.dirty.mark();
			}
		}
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

		PlayerData playerData = dataHandler.populatePlayerData(username, resultUuid);
		playerData.online.set(true);

		PacketRequestedPlayerData.sendForPlayer(connectionHandler, username);
		PacketRequestedInventory.sendForPlayer(connectionHandler, username);

		connectionHandler.sendPacket(new PacketNotificationFromCloud("Cloud connection established!", CloudConstants.CC_NAME, 0));
		for (UUID friend : playerData.friends) {
			PlayerData friendData = dataHandler.getPlayerData(friend);
			if (friendData != null && friendData.online.get()) {
				sendPacketToPlayer(friendData.username.get(), new PacketNotificationFromCloud(
					String.format("%s is online!", username), CloudConstants.CC_NAME, 0
				));
			}
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

				PlayerData playerData = dataHandler.getPlayerData(username);
				if (playerData != null) {
					playerData.online.set(false);

//					for (String user : playerData.friends) {
//						sendPacketToPlayer(user, new PacketNotificationFromCloud(username + " is offline!", CloudConstants.CC_NAME, 0));
//					}
				}
			}
			case CloudConstants.CHANNEL_SERVER -> {
				ServerStatus.remove(username);
			}
		}
	}

	@Override
	public void handleCommand(String command) {
		Commands.execute(command, new CloudSource());
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

	private class CloudSource implements CommandSource {
		@Override
		public String getName() {
			return Cloud.this.getCloudUsername();
		}

		@Override
		public boolean hasPermission(Permission permission) {
			return true;
		}

		@Override
		public void sendMessage(String message) {
			Cloud.this.log(message);
		}

		@Override
		public void sendMessage(String fmt, Object... args) {
			Cloud.this.log(fmt, args);
		}

		@Override
		public void sendPacket(G_TcpPacket packet) {
			Cloud.this.log("Received packet id %d from command.", packet.getPacketId());
		}
	}
}
