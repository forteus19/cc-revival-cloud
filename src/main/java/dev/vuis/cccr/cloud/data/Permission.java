package dev.vuis.cccr.cloud.data;

public record Permission(
	int id,
	String node
) {
	private static int lastId = 0;

	public Permission(String node) {
		this(lastId++, node);
		Permissions.INSTANCES.add(this);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Permission perm && id == perm.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}
