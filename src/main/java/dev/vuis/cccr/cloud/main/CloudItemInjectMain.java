package dev.vuis.cccr.cloud.main;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.item.impl.CloudItemGun;
import dev.vuis.cccr.cloud.util.RandomCollection;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public final class CloudItemInjectMain {
	private CloudItemInjectMain() {
	}

	@SneakyThrows
	static void main() {
		Map<Integer, String> prefixes = new HashMap<>();

		for (CloudItem item : CloudItems.defaults()) {
			if (!(item instanceof CloudItemGun)) {
				continue;
			}
			prefixes.put(item.getMcId(), item.getDisplayName());
		}

		Map<CloudItem, Field> fields = new HashMap<>();

		for (Field itemField : CloudItems.class.getFields()) {
			CloudItem item = (CloudItem) itemField.get(null);
			fields.put(item, itemField);

			if (item.isDefault()) {
				continue;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("public static final CloudItem ");
			sb.append(itemField.getName());
			sb.append(" = new ");

			switch (item) {
				case CloudItemGun gunItem -> {
					String prefix = prefixes.get(gunItem.getMcId());

					sb.append("CloudItemGun(");
					sb.append(gunItem.getId());
					sb.append(", ");
					sb.append(gunItem.getMcId() - 256);
					sb.append(", \"");
					sb.append(prefix);
					sb.append("\").setSuffix(\"");
					sb.append(gunItem.getDisplayName().replaceFirst("^" + prefix, "").trim());
					sb.append("\").setValue(CloudItemRarity.");
					sb.append(gunItem.getRarity().getLegacyName());
					sb.append(").setCollection(\"Prealpha\");");

					System.out.println(sb);
				}
				case CloudItemCase caseItem -> {
					sb.append("CloudItemCase(");
					sb.append(caseItem.getId());
					sb.append(", ");
					sb.append(caseItem.getMcId() - 256);
					sb.append(", \"\", ");
					sb.append(caseItem.getKeyItem().getId());
					sb.append(")");
					for (RandomCollection.Entry<CloudItem> entry : caseItem.contents.getEntries()) {
						sb.append(System.lineSeparator());
						sb.append("\t.addContent(");
						sb.append(fields.get(entry.objectSupplier().get()).getName());
						sb.append(", ");
						sb.append(entry.getWeight());
						sb.append(")");
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
