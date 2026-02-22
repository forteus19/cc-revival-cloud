package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public record PlayerGroup(
	String name,
	String tag,
	String color,
	Set<Permission> permissions
) {
	public PlayerGroup {
		PlayerGroups.TAG_MAP.put(tag.toUpperCase(Locale.ROOT), this);
	}

	public String displayTag() {
		return color + tag;
	}

	public void writeTag(FDSTagCompound tag) {
		tag.setString("groupName", name);
		tag.setString("groupNameTag", this.tag);
		tag.setString("groupColor", color);
		tag.setBoolean("groupQue", false);
		tag.setStringArrayList("permNodes", permissions.stream().map(Permission::node).collect(Collectors.toCollection(ArrayList::new)));
	}
}
