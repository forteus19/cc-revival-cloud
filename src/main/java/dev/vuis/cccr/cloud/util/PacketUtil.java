package dev.vuis.cccr.cloud.util;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;

public final class PacketUtil {
	private PacketUtil() {
	}

	public static boolean checkAuthentication(G_ITcpConnectionHandler connectionHandler) {
		return !Cloud.instance().getAuth().isAuthenticated(connectionHandler.getConnectionUsername());
	}

	public static boolean kickIfNotServer(G_ITcpConnectionHandler connectionHandler) {
		G_CloudTcpConnectionHandler cloudHandler = (G_CloudTcpConnectionHandler) connectionHandler;
		if (!cloudHandler.channel.equals(CloudConstants.CHANNEL_SERVER)) {
			cloudHandler.kickClientConnection("Attempted to send server packet on player channel");
			return true;
		}
		return false;
	}
}
