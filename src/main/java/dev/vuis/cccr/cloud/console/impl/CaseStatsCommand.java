package dev.vuis.cccr.cloud.console.impl;

import com.google.common.collect.Multiset;
import dev.vuis.cccr.cloud.console.Command;
import dev.vuis.cccr.cloud.console.CommandSource;
import dev.vuis.cccr.cloud.console.PlayerSource;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Permission;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.CloudItems;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

public class CaseStatsCommand implements Command {
	private static final Map<String, CloudItemCase> CASE_NAMES = Map.ofEntries(
		Map.entry("prestige", CloudItems.CASE_PRESTIGE),
		Map.entry("alpha", CloudItems.CASE_ALPHA),
		Map.entry("beta", CloudItems.CASE_BETA),
		Map.entry("gamma", CloudItems.CASE_GAMMA),
		Map.entry("delta", CloudItems.CASE_DELTA),
		Map.entry("epsilon", CloudItems.CASE_EPSILON),
		Map.entry("chronical", CloudItems.CASE_CHRONICAL),
		Map.entry("mythic", CloudItems.CASE_MYTHIC),
		Map.entry("oasis", CloudItems.CASE_OASIS),
		Map.entry("clash", CloudItems.CASE_CLASH),
		Map.entry("elemental", CloudItems.CASE_ELEMENTAL),
		Map.entry("revolution", CloudItems.CASE_REVOLUTION)
	);

	@Override
	public String getName() {
		return "casestats";
	}

	@Override
	public String getUsage() {
		return "<case?>";
	}

	@Override
	public Permission getPermission() {
		return null;
	}

	@Override
	public void run(String[] args, CommandSource source) {
		if (!(source instanceof PlayerSource)) {
			source.sendMessage("Only players can run this command!");
			return;
		}
		CloudItemCase targetCase = null;
		if (args.length == 1) {
			targetCase = CASE_NAMES.get(args[0]);
			if (targetCase == null) {
				source.sendMessage("Unknown case '%s'.", args[0]);
				return;
			}
		}

		PlayerInventory inventory = DataHandler.instance().getPlayerInventory(source.getName());
		if (inventory == null) {
			return;
		}

		if (targetCase == null) {
			if (inventory.caseStats.isEmpty()) {
				source.sendMessage("No case stats available.");
				return;
			}
			for (Map.Entry<CloudItemCase, Multiset<CloudItem>> entry : inventory.caseStats.entrySet()) {
				sendCaseStats(entry.getKey(), entry.getValue(), source);
			}
		} else {
			Multiset<CloudItem> resultItems = inventory.caseStats.get(targetCase);
			if (resultItems == null) {
				source.sendMessage("No stats for case '%s'.", args[0]);
				return;
			}
			sendCaseStats(targetCase, resultItems, source);
		}
	}

	private static void sendCaseStats(CloudItemCase caseItem, Multiset<CloudItem> resultItems, CommandSource source) {
		source.sendMessage("===== %s =====", caseItem.getDisplayName().toUpperCase(Locale.ROOT));
		source.sendMessage("> TOTAL: %d", resultItems.size());

		resultItems.entrySet().stream().sorted(Comparator.comparing(e -> e.getElement().getRarity())).forEachOrdered(entry -> {
			CloudItem item = entry.getElement();
			source.sendMessage("%s%s: %d", item.getRarity().getFormattingCode(), item.getDisplayName(), entry.getCount());
		});
	}
}
