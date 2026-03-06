package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.util.List;

public final class MarketStore {
	public static final List<Category> CATEGORIES = List.of(
		new Category("Cases 1", "Cases / Case Keys", List.of(
			new Item(CloudItems.CASE_ALPHA, 100, 15),
			new Item(CloudItems.CASE_KEY_ALPHA, 25, 15),
			new Item(CloudItems.CASE_BETA, 100, 15),
			new Item(CloudItems.CASE_KEY_BETA, 25, 15),
			new Item(CloudItems.CASE_GAMMA, 100, 15),
			new Item(CloudItems.CASE_KEY_GAMMA, 25, 15)
		)),
		new Category("2", "Cases / Case Keys", List.of(
			new Item(CloudItems.CASE_DELTA, 100, 15),
			new Item(CloudItems.CASE_KEY_DELTA, 25, 15),
			new Item(CloudItems.CASE_EPSILON, 100, 15),
			new Item(CloudItems.CASE_KEY_EPSILON, 25, 15),
			new Item(CloudItems.CASE_CHRONICAL, 100, 15),
			new Item(CloudItems.CASE_KEY_CHRONICAL, 25, 15)
		)),
		new Category("3", "Cases / Case Keys", List.of(
			new Item(CloudItems.CASE_MYTHIC, 100, 15),
			new Item(CloudItems.CASE_KEY_MYTHIC, 25, 15),
			new Item(CloudItems.CASE_OASIS, 100, 15),
			new Item(CloudItems.CASE_KEY_OASIS, 25, 15),
			new Item(CloudItems.CASE_CLASH, 100, 15),
			new Item(CloudItems.CASE_KEY_CLASH, 25, 15)
		)),
		new Category("4", "Cases / Case Keys", List.of(
			new Item(CloudItems.CASE_ELEMENTAL, 100, 15),
			new Item(CloudItems.CASE_KEY_ELEMENTAL, 25, 15),
			new Item(CloudItems.CASE_REVOLUTION, 100, 15),
			new Item(CloudItems.CASE_KEY_REVOLUTION, 25, 15),
			new Item(CloudItems.CASE_VECTORIZED, 100, 15),
			new Item(CloudItems.CASE_KEY_VECTORIZED, 25, 15)
		)),
		new Category("5", "Cases / Case Keys", List.of(
			new Item(CloudItems.CASE_BIOMECHANIC, 100, 15),
			new Item(CloudItems.CASE_KEY_BIOMECHANIC, 25, 15),
			new Item(CloudItems.CASE_PHANTASMAGORIA, 100, 15),
			new Item(CloudItems.CASE_KEY_PHANTASMAGORIA, 25, 15),
			new Item(CloudItems.CASE_SEPULCHER, 100, 15),
			new Item(CloudItems.CASE_KEY_SEPULCHER, 25, 15)
		)),
		new Category("Misc", "Miscellaneous Items", List.of(
			new Item(CloudItems.NAME_TAG, 50, 10)
		))
	);

	private MarketStore() {
	}

	public static void writeTag(FDSTagCompound tag) {
		tag.setInteger("size", CATEGORIES.size());
		for (int i = 0; i < CATEGORIES.size(); i++) {
			Category category = CATEGORIES.get(i);
			FDSTagCompound categoryTag = new FDSTagCompound("cat" + i);
			category.writeTag(categoryTag, i);

			tag.setTagCompound("cat" + i, categoryTag);
		}
	}

	public record Category(
		String name,
		String description,
		List<Item> items
	) {
		public void writeTag(FDSTagCompound tag, int index) {
			tag.setInteger("id", index);
			tag.setString("name", name);
			tag.setString("desc", description);

			tag.setInteger("size", items.size());
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				FDSTagCompound itemTag = new FDSTagCompound("item" + i);
				item.writeTag(itemTag, i);

				tag.setTagCompound("item" + i, itemTag);
			}
		}
	}

	public record Item(
		CloudItem item,
		int price,
		String description,
		int maxQuantity
	) {
		public Item(CloudItem item, int price, int maxQuantity) {
			this(item, price, "", maxQuantity);
		}

		public void writeTag(FDSTagCompound tag, int index) {
			tag.setInteger("id", index);
			tag.setInteger("cloudid", item.getId());
			tag.setString("name", item.getDisplayName());
			tag.setInteger("price", price);
			tag.setString("desc", description);
			tag.setInteger("maxq", maxQuantity);
		}
	}
}
