package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.gui.ServerGui;

public final class Main {
	private Main() {
	}

	static void main(String[] args) {
		G_CloudNetworkManager.LOG_LOGIN_PROCESS = true;

		Cloud cloud = new Cloud();

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
