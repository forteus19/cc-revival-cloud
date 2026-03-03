package dev.vuis.cccr.cloud.main;

import com.f3rullo14.cloud.server.G_CloudNetworkManager;
import com.f3rullo14.cloud.server.gui.ServerGui;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.PacketRegistry;
import dev.vuis.cccr.cloud.util.Util;
import lombok.Cleanup;

public final class CloudMain {
	private static final int TICK_MS = 50;

	private CloudMain() {
	}

	static void main(String[] args) {
		int port = Util.getEnvInt("CCCR_PORT", 1923);
		String mmPassword = System.getenv("CCCR_MM_PASSWORD");
		String mcHost = Util.getEnv("CCCR_MC_HOST", "localhost");
		String dbHost = Util.getEnvOrThrow("CCCR_DB_HOST");
		String dbPort = Util.getEnvOrThrow("CCCR_DB_PORT");
		String dbName = Util.getEnvOrThrow("CCCR_DB_NAME");
		String dbUser = Util.getEnvOrThrow("CCCR_DB_USER");
		String dbPass = Util.getEnvOrThrow("CCCR_DB_PASS");

		boolean gui = args.length >= 1 && args[0].equals("gui");

//		G_CloudNetworkManager.LOG_PACKETS_PROCESSED = true;
		G_CloudNetworkManager.LOG_LOGIN_PROCESS = true;
		PacketRegistry.registerPackets();

		HikariConfig dbConfig = new HikariConfig();
		dbConfig.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName));
		dbConfig.setUsername(dbUser);
		dbConfig.setPassword(dbPass);

		@Cleanup
		HikariDataSource dataSource = new HikariDataSource(dbConfig);

		Cloud cloud = new Cloud(port, mmPassword, mcHost, dataSource);

		if (gui) {
			ServerGui.func_120016_a(cloud.getCloudManager());
		}

		cloud.onStartup();

		Thread consoleThread = new Thread(() -> {
			while (!cloud.getCloudManager().isCloudStopped) {
				String command = IO.readln();
				if (command == null) {
					break;
				}
				try {
					cloud.handleCommand(command);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		consoleThread.start();

		while (!cloud.getCloudManager().isCloudStopped) {
			try {
				cloud.onUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

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
