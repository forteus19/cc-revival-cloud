package dev.vuis.ccrevival.cloud;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.ArrayList;
import lombok.Getter;

public final class Cloud implements ICloud {
	boolean isRunning = true;

	@Getter
	private final G_CloudManager cloudManager = new G_CloudManager(this);
	{
		cloudManager.getNetwork().registerPacketHandler(new CloudPacketHandler());
	}

	void onUpdate() {
		cloudManager.onUpdate();
	}

	void onShutdown() {
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
		return 1490;
	}

	@Override
	public void getDisplayInformation(ArrayList<String> lines) {
	}

	@Override
	public boolean onConnectionReached(G_CloudTcpConnectionHandler connectionHandler) {
		cloudManager.log("Connection reached with player \"" + connectionHandler.getConnectionUsername() + "\"");
		return true;
	}

	@Override
	public boolean onConnectionAddedToThread(G_CloudTcpConnectionHandler connectionHandler) {
		return true;
	}

	@Override
	public void onConnectionEstablished(G_CloudTcpConnectionHandler connectionHandler, FDSTagCompound loginData) {
		cloudManager.log("Connection established with player \"" + connectionHandler.getConnectionUsername() + "\"");
	}

	@Override
	public void onConnectionDisconnected(G_CloudTcpConnectionHandler connectionHandler) {
	}

	@Override
	public void handleCommand(String command) {
		if (command.equals("stop")) {
			isRunning = false;
		}
	}

	@Override
	public void getChannels(ArrayList<String> target) {
	}
}
