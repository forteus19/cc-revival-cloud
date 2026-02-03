package dev.vuis.cccr.cloud.util;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.UUID;

public final class FdsUtil {
	private FdsUtil() {
	}

	public static UUID getUuidOrDefault(FDSTagCompound tag, String key, UUID def) {
		String value = tag.getString(key);
		if (value == null) {
			return def;
		}
		try {
			return UUID.fromString(value);
		} catch (IllegalArgumentException e1) {
			try {
				return Util.parseUndashedUuid(value);
			} catch (IllegalArgumentException e2) {
				return def;
			}
		}
	}
}
