package dev.vuis.cccr.cloud.data;

import java.util.concurrent.atomic.AtomicBoolean;

public class DirtyHandler {
	private final AtomicBoolean dirty = new AtomicBoolean(false);

	public void mark() {
		dirty.compareAndSet(false, true);
	}

	public void clear() {
		dirty.compareAndSet(true, false);
	}

	public boolean isDirty() {
		return dirty.get();
	}
}
