package dev.vuis.ccrevival.cloud;

import com.f3rullo14.cloud.server.gui.ServerGui;

public final class Main {
	private Main() {
	}

	static void main(String[] args) {
		Cloud cloud = new Cloud();

		ServerGui.func_120016_a(cloud.getCloudManager());

		while (cloud.isRunning) {
			cloud.onUpdate();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				cloud.isRunning = false;
			}
		}

		cloud.onShutdown();
	}
}
