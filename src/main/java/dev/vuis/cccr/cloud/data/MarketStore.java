package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import java.util.List;

public final class MarketStore {
	public static final List<Category> CATEGORIES = List.of(
		new Category("Cases 1", "", List.of(
			new Item(CloudItems.CASE_ALPHA1, 100, 15),
			new Item(CloudItems.CASE_ALPHA2, 100, 15),
			new Item(CloudItems.CASE_ALPHA3, 100, 15),
			new Item(CloudItems.CASE_HAT, 100, 15),
			new Item(CloudItems.CASE_HALLOWEEN, 100, 15),
			new Item(CloudItems.CASE_EXO, 100, 15)
		)),
		new Category("Cases 2", "", List.of(
			new Item(CloudItems.CASE_PREALPHA, 100, 15)
		)),
		new Category("Keys 1", "Item Case/Capsule Keys.", List.of(
			new Item(CloudItems.CASE_KEY_ALPHA, 25, 15),
			new Item(CloudItems.CASE_KEY_ALPHA2, 25, 15),
			new Item(CloudItems.CASE_KEY_ALPHA3, 25, 15),
			new Item(CloudItems.CASE_KEY_HAT, 25, 15),
			new Item(CloudItems.CASE_KEY_HALLOWEEN, 25, 15),
			new Item(CloudItems.CASE_KEY_EXO, 25, 15)
		)),
		new Category("Keys 2", "Item Case/Capsule Keys.", List.of(
			new Item(CloudItems.CASE_KEY_PREALPHA, 25, 15)
		)),
		new Category("Misc", "", List.of(
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
