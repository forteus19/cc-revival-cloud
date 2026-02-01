package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;

public final class CloudStats {
	public static boolean compRequiresRank = false;
	public static boolean compActive = true;

	private CloudStats() {
	}

	public static void putTag(FDSTagCompound tag) {
		tag.setInteger("usersonline", Cloud.getInstance().getCloudManager().getNetwork().getNetChannel(CloudConstants.CHANNEL_PLAYER).size());
		tag.setBoolean("compRequiresRank", compRequiresRank);
		tag.setBoolean("compactive", compActive);
	}
}
