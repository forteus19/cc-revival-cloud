package dev.vuis.cccr.cloud.data;

public record Permission(
	int id,
	String node
) {
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Permission perm && id == perm.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}
