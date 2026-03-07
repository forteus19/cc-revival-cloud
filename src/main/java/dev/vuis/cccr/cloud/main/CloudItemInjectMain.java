package dev.vuis.cccr.cloud.main;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.item.impl.CloudItemGun;
import dev.vuis.cccr.cloud.item.impl.CloudItemKnife;
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

			if (item.isDefault()) {
				continue;
			}

			int expectedMcId = item.getMcId() - 256;
			boolean print = false;

			StringBuilder sb = new StringBuilder();
			sb.append("public static final CloudItem ");
			sb.append(itemField.getName());
			sb.append(" = new ");

			switch (item) {
				case CloudItemGun gunItem -> {
					print = true;

					String collection = gunItem.getCollection() == null ? "Cheats" : gunItem.getCollection().getDisplayName();

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
					sb.append(").setCollection(\"");
					sb.append(collection);
					sb.append("\").setFilterMT(\"");
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
				}
				case CloudItemKnife knifeItem -> {
					print = true;

					sb.append("CloudItemKnife(");
					sb.append(knifeItem.getId());
					sb.append(", ");
					sb.append(expectedMcId);
					sb.append(", \"");
					sb.append(knifeItem.getPrefix());
					sb.append("\").setSuffix(\"");
					sb.append(knifeItem.getSuffix());
					sb.append("\").setValue(CloudItemRarity.TIER6).setFilterMT(\"knife\");");
				}
				case CloudItemCase caseItem -> {
					print = true;

					sb.append("CloudItemCase(");
					sb.append(caseItem.getId());
					sb.append(", ");
					sb.append(expectedMcId);
					sb.append(", \"\", ");
					sb.append(caseItem.getKeyItem().getId());
					sb.append(")");
					for (RandomCollection.Entry<CloudItem> entry : caseItem.contents.getEntries()) {
						sb.append(System.lineSeparator());
						sb.append("\t.addContent(");
						sb.append(fields.get(entry.objectSupplier().get()).getName());
						sb.append(", 0)");
					}
					sb.append(System.lineSeparator());
					sb.append("\t.setSuffix(\"Case\").setValue(CloudItemRarity.TIER1).setFilterMT(\"case\").disableNameable().disableShowcase().disableContracts();");
				}
				default -> {
				}
			}

			if (print) {
				System.out.println(sb);
				System.out.println();
			}
		}
	}
}
