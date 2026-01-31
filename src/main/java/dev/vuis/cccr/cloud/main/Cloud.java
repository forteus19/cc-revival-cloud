package dev.vuis.cccr.cloud.main;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.util.ReflectUtil;
import java.util.ArrayList;
import lombok.Getter;

public final class Cloud implements ICloud {
	@Getter
	private final G_CloudManager cloudManager = new G_CloudManager(this);

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
		return 1923;
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
		cloudManager.log("Client \"" + connectionHandler.getConnectionUsername() + "\" established connection");
		cloudManager.log("Login info: " + loginData);

		PacketRequestedPlayerData packet = new PacketRequestedPlayerData();
		packet.username = connectionHandler.getConnectionUsername();
		packet.tag.setInteger("exp", 99999);
		packet.tag.setBoolean("online", true);
		packet.tag.setBoolean("enableNotification_FriendRequest", true);
		packet.tag.setBoolean("enableNotification_FriendPoke", true);
		packet.tag.setBoolean("enableNotification_TradeRequest", true);
		packet.tag.setBoolean("enableProfileViewing", true);
		packet.tag.setBoolean("enableTradeRequests", true);
		packet.tag.setBoolean("enableFriendRequests", true);
		packet.tag.setString("mood", "super awesome mood");
		packet.tag.setString("status", "Online");
		packet.tag.setString("clantag", "10MT");
		packet.tag.setString("playerUUID", "d4fb9430-8a41-4ad8-ba4e-1c87b1360359");
		packet.tag.setBoolean("twoFactorEnable", false);
		packet.tag.setBoolean("isSupporter", false);
		packet.tag.setBoolean("enableCape", false);
		packet.tag.setBoolean("hasparty", false);
		connectionHandler.sendPacket(packet);
	}

	@Override
	public void onConnectionDisconnected(G_CloudTcpConnectionHandler connectionHandler) {
		cloudManager.log("Client \"" + connectionHandler.getConnectionUsername() + "\" disconnected (reason: \"" + ReflectUtil.getTerminationReason(connectionHandler.tcpConnection) + "\")");
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
