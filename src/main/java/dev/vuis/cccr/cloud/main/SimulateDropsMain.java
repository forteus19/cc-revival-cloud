package dev.vuis.cccr.cloud.main;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.util.RandomCollection;

public final class SimulateDropsMain {
	static void main() {
		RandomCollection<CloudItem> contents = CloudItems.CASE_ALPHA1.contents;
		CloudItem target = CloudItems.KNIFE_TAC_DISPLAY;
		int drops = 10000000;

		int hits = 0;
		for (int i = 0 ; i < drops; i++) {
			if (contents.getRandom() == target) {
				hits++;
			}
		}

		System.out.printf("%.4f%n", (hits / (double) drops) * 100.0);
	}
}
