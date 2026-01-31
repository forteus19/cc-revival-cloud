package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import com.f3rullo14.cloud.server.ICloud;
import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.ArrayList;
import lombok.Getter;

public final class Cloud implements ICloud {
	@Getter
	private final G_CloudManager cloudManager = new G_CloudManager(this);
	{
		cloudManager.getNetwork().registerPacketHandler(new CloudPacketHandler());
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
	}

	@Override
	public void onConnectionDisconnected(G_CloudTcpConnectionHandler connectionHandler) {
		cloudManager.log("Client \"" + connectionHandler.getConnectionUsername() + "\" disconnected");
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
