package dev.vuis.cccr.cloud.util;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.CloudConstants;

public final class Util {
	private Util() {
	}

	public static String getEnv(String env, String def) {
		String value = System.getenv(env);
		if (value == null) {
			return def;
		} else {
			return value;
		}
	}

	public static int getEnvInt(String env, int def) {
		String value = System.getenv(env);
		if (value == null) {
			return def;
		} else {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return def;
			}
		}
	}

	public static boolean kickIfServer(G_ITcpConnectionHandler connectionHandler) {
		if (!(connectionHandler instanceof G_CloudTcpConnectionHandler cloudHandler)) {
			return true;
		}
		if (!cloudHandler.channel.equals(CloudConstants.CHANNEL_SERVER)) {
			cloudHandler.kickClientConnection("Attempted to send server packet on player channel");
			return true;
		}
		return false;
	}
}
