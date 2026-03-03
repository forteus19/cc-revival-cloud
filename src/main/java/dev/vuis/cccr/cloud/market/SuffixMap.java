package dev.vuis.cccr.cloud.market;

import dev.vuis.cccr.cloud.item.CloudItem;
import dev.vuis.cccr.cloud.item.SuffixedCloudItem;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public sealed interface SuffixMap {
	Collection<CloudItem> getAll();

	Collection<CloudItem> get(String suffix);

	void put(String suffix, CloudItem item);

	static SuffixMap createForItem(CloudItem item) {
		return item instanceof SuffixedCloudItem ? new Default() : new Unsuffixed();
	}

	final class Default implements SuffixMap {
		private final Map<String, CloudItem> suffixMap = new HashMap<>();

		@Override
		public Collection<CloudItem> getAll() {
			return Collections.unmodifiableCollection(suffixMap.values());
		}

		@Override
		public Collection<CloudItem> get(String suffix) {
			return suffix != null ? Set.of(suffixMap.get(suffix)) : Set.of();
		}

		@Override
		public void put(String suffix, CloudItem item) {
			if (suffix != null) {
				suffixMap.put(suffix, item);
			}
		}
	}

	final class Unsuffixed implements SuffixMap {
		private final Set<CloudItem> entries = new HashSet<>();

		@Override
		public Collection<CloudItem> getAll() {
			return Collections.unmodifiableCollection(entries);
		}

		@Override
		public Collection<CloudItem> get(String suffix) {
			return getAll();
		}

		@Override
		public void put(String suffix, CloudItem item) {
			entries.add(item);
		}
	}
}
