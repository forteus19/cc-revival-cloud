package dev.vuis.cccr.cloud.main;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.gui.ServerGui;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.PacketRegistry;
import dev.vuis.cccr.cloud.util.Util;

public final class CloudMain {
	private static final int TICK_MS = 50;

	private CloudMain() {
	}

	static void main(String[] args) {
		boolean gui = args.length >= 1 && args[0].equals("gui");

//		G_CloudNetworkManager.LOG_PACKETS_PROCESSED = true;
		G_CloudNetworkManager.LOG_LOGIN_PROCESS = true;
		PacketRegistry.registerPackets();

		Cloud cloud = new Cloud(
			Util.getEnvInt("CCCR_PORT", 1923),
			Util.getEnv("CCCR_MM_PASSWORD", null),
			Util.getEnv("CCCR_MC_HOST", "localhost")
		);

		if (gui) {
			ServerGui.func_120016_a(cloud.getCloudManager());
		}

		new Thread(() -> {
			while (!cloud.getCloudManager().isCloudStopped) {
				cloud.handleCommand(IO.readln());
			}
		}).start();

		while (!cloud.getCloudManager().isCloudStopped) {
			cloud.onUpdate();

			try {
				Thread.sleep(TICK_MS);
			} catch (InterruptedException e) {
				cloud.getCloudManager().initiateShutdown();
			}
		}

		cloud.onShutdown();
		System.exit(0);
	}
}
