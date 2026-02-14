package dev.vuis.cccr.cloud.main;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.util.RandomCollection;
import java.util.Locale;

public final class DropChancesMain {
	private DropChancesMain() {
	}

	static void main() {
		for (CloudItem item : CloudItems.all()) {
			if (!(item instanceof CloudItemCase caseItem)) {
				continue;
			}
			System.out.printf("===== %s =====%n", caseItem.getDisplayName().toUpperCase(Locale.ROOT));

			float totalWeight = (float) caseItem.contents.getTotalWeight();
			for (RandomCollection.Entry<CloudItem> entry : caseItem.contents.getEntries()) {
				float chance = (entry.getWeight() / totalWeight) * 100f;
				System.out.printf("%s: %.2f%%%n", entry.objectSupplier().get().getDisplayName(), chance);
			}

			System.out.println();
		}
	}
}
