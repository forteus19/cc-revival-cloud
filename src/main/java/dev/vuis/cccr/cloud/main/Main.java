package dev.vuis.cccr.cloud.main;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.gui.ServerGui;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.PacketRegistry;
import dev.vuis.cccr.cloud.util.Util;

public final class Main {
	private Main() {
	}

	static void main(String[] args) {
//		G_CloudNetworkManager.LOG_PACKETS_PROCESSED = true;
		G_CloudNetworkManager.LOG_LOGIN_PROCESS = true;
		PacketRegistry.registerPackets();

		Cloud cloud = new Cloud(
			Util.getEnvInt("CCCR_PORT", 1923),
			Util.getEnv("CCCR_MM_PASSWORD", null),
			Util.getEnv("CCCR_MC_HOST", "localhost")
		);

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
