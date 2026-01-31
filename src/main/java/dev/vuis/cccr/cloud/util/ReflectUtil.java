package dev.vuis.cccr.cloud.util;

import com.f3rullo14.cloud.G_TcpConnection;
import java.lang.reflect.Field;

public final class ReflectUtil {
	private static final Field TERMINATION_REASON_FIELD;

	static {
		try {
			TERMINATION_REASON_FIELD = G_TcpConnection.class.getDeclaredField("terminationReason");
			TERMINATION_REASON_FIELD.setAccessible(true);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	private ReflectUtil() {
	}

	public static String getTerminationReason(G_TcpConnection tcpConnection) {
		try {
			return (String) TERMINATION_REASON_FIELD.get(tcpConnection);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
