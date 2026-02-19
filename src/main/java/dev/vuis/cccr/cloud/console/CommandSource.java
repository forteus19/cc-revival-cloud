package dev.vuis.cccr.cloud.console;

import com.f3rullo14.cloud.G_TcpPacket;
import dev.vuis.cccr.cloud.data.Permission;

public interface CommandSource {
	String getName();

	boolean hasPermission(Permission permission);

	void sendMessage(String message);

	default void sendMessage(String fmt, Object... args) {
		sendMessage(String.format(fmt, args));
	}

	void sendPacket(G_TcpPacket packet);
}
