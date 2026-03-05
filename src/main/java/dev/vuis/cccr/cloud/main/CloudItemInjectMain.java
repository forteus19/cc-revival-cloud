package dev.vuis.cccr.cloud.main;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.item.impl.CloudItemGun;
import dev.vuis.cccr.cloud.util.RandomCollection;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.SneakyThrows;

public final class CloudItemInjectMain {
	private CloudItemInjectMain() {
	}

	@SneakyThrows
	static void main() {
		Map<CloudItem, Field> fields = new HashMap<>();

		for (Field itemField : CloudItems.class.getFields()) {
			if (!(itemField.get(null) instanceof CloudItem item)) {
				continue;
			}
			fields.put(item, itemField);

			if (item.isDefault() || (item instanceof CloudItemGun gunItem && gunItem.getCollection() == null)) {
				continue;
			}

			int expectedMcId = item.getMcId() - 256;

			StringBuilder sb = new StringBuilder();
			sb.append("public static final CloudItem ");
			sb.append(itemField.getName());
			sb.append(" = new ");

			switch (item) {
				case CloudItemGun gunItem -> {
					sb.append("CloudItemGun(");
					sb.append(gunItem.getId());
					sb.append(", ");
					sb.append(expectedMcId);
					sb.append(", \"");
					sb.append(gunItem.getPrefix());
					sb.append("\").setSuffix(\"");
					sb.append(gunItem.getSuffix());
					sb.append("\").setValue(CloudItemRarity.");
					sb.append(gunItem.getRarity().getLegacyName());
					sb.append(")");
					String collection = gunItem.getCollection().getName();
					if (collection != null) {
						sb.append(".setCollection(\"");
						sb.append(collection);
						sb.append("\")");
					}
					sb.append(".setFilterMT(\"");
					sb.append(gunItem.getMarketCategory().name().toLowerCase(Locale.ROOT));
					sb.append("\");");

					sb.append(System.lineSeparator());

					sb.append("CloudItem.");
					sb.append(itemField.getName());
					sb.append(".setValue(CloudItemRarity.");
					sb.append(gunItem.getRarity().getLegacyName());
					sb.append(").setCollection(\"");
					sb.append(collection);
					sb.append("\");");

					System.out.println(sb);
				}
				case CloudItemCase caseItem -> {
					sb.append("CloudItemCase(");
					sb.append(caseItem.getId());
					sb.append(", ");
					sb.append(expectedMcId);
					sb.append(", \"\", ");
					sb.append(caseItem.getKeyItem().getId());
					sb.append(")");
					for (RandomCollection.Entry<CloudItem> entry : caseItem.contents.getEntries().reversed()) {
						sb.append(System.lineSeparator());
						sb.append("\t.addContent(");
						sb.append(fields.get(entry.objectSupplier().get()).getName());
						sb.append(", 0)");
					}
					sb.append(".setSuffix(\"Case\").setValue(CloudItemRarity.TIER1).disableNameable().disableShowcase().disableContracts();");

					System.out.println(sb);
				}
				default -> {
				}
			}
		}
	}
}
