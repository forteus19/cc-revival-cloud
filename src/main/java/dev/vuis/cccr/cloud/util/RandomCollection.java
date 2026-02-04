package dev.vuis.cccr.cloud.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RandomCollection<T> {
	private final List<Entry<T>> entries = new ArrayList<>();
	private int totalWeight = 0;

	public RandomCollection(T[] objects, Function<T, Integer> weightGetter) {
		for (T object : objects) {
			add(object, weightGetter.apply(object));
		}
	}

	public RandomCollection<T> add(T object, int weight) {
		if (weight < 0) {
			throw new IllegalArgumentException("weight");
		}
		if (weight == 0) {
			return this;
		}
		entries.add(new Entry<>(object, totalWeight, totalWeight += weight));
		return this;
	}

	public T getRandom() {
		int threshold = ThreadLocalRandom.current().nextInt(totalWeight);
		for (Entry<T> entry : entries) {
			if (threshold >= entry.minThreshold() && threshold < entry.maxThreshold()) {
				return entry.object();
			}
		}
		throw new AssertionError();
	}

	public List<T> getRandomBulk(int count) {
		List<T> objects = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			objects.add(getRandom());
		}
		return objects;
	}

	private record Entry<T>(
		T object,
		int minThreshold,
		int maxThreshold
	) {
	}
}
