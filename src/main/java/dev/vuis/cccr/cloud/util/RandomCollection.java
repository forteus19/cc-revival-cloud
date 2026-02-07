package dev.vuis.cccr.cloud.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RandomCollection<T> {
	private final List<Entry<T>> entries = new ArrayList<>();
	private final List<Entry<T>> bulkEntries = new ArrayList<>();
	private int totalWeight = 0;
	private int totalWeightBulk = 0;

	public RandomCollection(T[] objects, Function<T, Integer> weightGetter) {
		for (T object : objects) {
			add(object, weightGetter.apply(object), true);
		}
	}

	public RandomCollection<T> add(T object, int weight) {
		return add(object, weight, true);
	}

	public RandomCollection<T> add(T object, int weight, boolean includeBulk) {
		return add(() -> object, weight, includeBulk);
	}

	public RandomCollection<T> add(List<T> objectList, int weight, boolean includeBulk) {
		return add(() -> objectList.get(ThreadLocalRandom.current().nextInt(objectList.size())), weight, includeBulk);
	}

	public RandomCollection<T> add(Supplier<T> objectSupplier, int weight, boolean includeBulk) {
		if (weight < 0) {
			throw new IllegalArgumentException("weight");
		}
		if (weight == 0) {
			return this;
		}

		entries.add(new Entry<>(objectSupplier, totalWeight, totalWeight += weight));
		if (includeBulk) {
			bulkEntries.add(new Entry<>(objectSupplier, totalWeightBulk, totalWeightBulk += weight));
		}

		return this;
	}

	public T getRandom() {
		return getRandom(false);
	}

	public T getRandom(boolean useBulk) {
		List<Entry<T>> targetEntries = useBulk ? bulkEntries : entries;

		int threshold = ThreadLocalRandom.current().nextInt(useBulk ? totalWeightBulk : totalWeight);
		for (Entry<T> entry : targetEntries) {
			if (threshold >= entry.minThreshold() && threshold < entry.maxThreshold()) {
				return entry.objectSupplier().get();
			}
		}

		throw new AssertionError();
	}

	public List<T> getRandomBulk(int count) {
		List<T> objects = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			objects.add(getRandom(true));
		}
		return objects;
	}

	private record Entry<T>(
		Supplier<T> objectSupplier,
		int minThreshold,
		int maxThreshold
	) {
	}
}
