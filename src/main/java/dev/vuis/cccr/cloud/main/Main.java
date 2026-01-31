package dev.vuis.cccr.cloud.main;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.gui.ServerGui;
import dev.vuis.cccr.cloud.PacketRegistry;

public final class Main {
	private Main() {
	}

	static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Args: <port>");
			return;
		}

		int port = Integer.parseInt(args[0]);
		if (port >= 10000) {
			System.err.println("Port must less than 10000");
			return;
		}

//		G_CloudNetworkManager.LOG_PACKETS_PROCESSED = true;
		G_CloudNetworkManager.LOG_LOGIN_PROCESS = true;
		PacketRegistry.registerPackets();

		Cloud cloud = new Cloud(port);

		ServerGui.func_120016_a(cloud.getCloudManager());

		while (!cloud.getCloudManager().isCloudStopped) {
			cloud.onUpdate();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				cloud.getCloudManager().initiateShutdown();
			}
		}

		cloud.onShutdown();
		System.exit(0);
	}
}
