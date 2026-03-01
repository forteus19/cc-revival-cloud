package dev.vuis.cccr.cloud.data;

import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import dev.vuis.cccr.cloud.util.Util;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public final class PlayerData {
	public final DirtyHandler dirty = new DirtyHandler();
	public final DirtyHandler punishmentsDirty = new DirtyHandler();
	public final DirtyHandler friendsDirty = new DirtyHandler();
	public final DirtyHandler friendRequestsDirty = new DirtyHandler();

	// player data
	public final UUID uuid;
	public final AtomicReference<String> username;
	public final AtomicInteger trophies = new AtomicInteger(0);
	public final AtomicInteger kills = new AtomicInteger(0);
	public final AtomicInteger deaths = new AtomicInteger(0);
	public final AtomicInteger games = new AtomicInteger(0);
	public final AtomicInteger firstBloods = new AtomicInteger(0);
	public final AtomicInteger matchKarma = new AtomicInteger(0);
	public final AtomicInteger minutesPlayed = new AtomicInteger(0);
	public final AtomicInteger exp = new AtomicInteger(0);
	public final AtomicInteger emeralds = new AtomicInteger(999999999); // FIXME on alpha release
	public final AtomicReference<String> mood = new AtomicReference<>();
	public final AtomicInteger compExp = new AtomicInteger(0);
	public final AtomicInteger prestige = new AtomicInteger(0);
	public final Set<Integer> achievements = ConcurrentHashMap.newKeySet();
	public final AtomicReference<PlayerGroup> group = new AtomicReference<>();
	public final AtomicInteger score = new AtomicInteger(0);

	// player state
	public final AtomicBoolean online = new AtomicBoolean(false);
	public final AtomicReference<String> status = new AtomicReference<>("Main Menu");
	public final AtomicReference<SearchHandler.JoinData> lastComp = new AtomicReference<>();

	// friends
	public final Set<UUID> friends = ConcurrentHashMap.newKeySet();
	public final Set<UUID> friendRequests = ConcurrentHashMap.newKeySet();

	// punishments
	public final List<Punishment> punishments = new CopyOnWriteArrayList<>();

	PlayerData(UUID uuid, String username) {
		this.uuid = uuid;
		this.username = new AtomicReference<>(username);
	}

	public boolean hasPermission(Permission permission) {
		PlayerGroup groupGet = group.get();
		if (groupGet == null) {
			return false;
		}
		Set<Permission> permissions = groupGet.permissions();
		return permissions.contains(permission) || permissions.contains(Permissions.ALL);
	}

	public boolean hasActivePunishment(PunishmentType... types) {
		Set<PunishmentType> typesSet = Set.of(types);
		LocalDateTime now = Util.utcNow();
		return punishments.stream().anyMatch(pun -> typesSet.contains(pun.type()) && pun.end().isAfter(now));
	}

	public void writeDataTag(FDSTagCompound tag, int coinId, boolean writePunishments) {
		tag.setInteger("trophies", trophies.get());
		tag.setInteger("kills", kills.get());
		tag.setInteger("deaths", deaths.get());
		tag.setInteger("games", games.get());
		tag.setInteger("statfb", firstBloods.get());
		tag.setInteger("matchKarma", matchKarma.get());
		if (lastComp.get() != null) {
			tag.setBoolean("lastcomp", true);
		}
		tag.setInteger("time", minutesPlayed.get() * 60);
		tag.setInteger("exp", exp.get());
		tag.setBoolean("online", online.get());
		tag.setInteger("emeralds", emeralds.get());
		String moodGet = mood.get();
		tag.setString("mood", moodGet != null ? moodGet : "");
		tag.setString("status", status.get());
		tag.setInteger("coinDisplayed", coinId);
		int compExpGet = compExp.get();
		CompetitiveRank compRank = CompetitiveRank.getRankFromExp(compExpGet);
		tag.setInteger("compRank", compRank.ordinal());
		tag.setInteger("compRankXP", compRank.getExpProgress(compExpGet));
		tag.setInteger("compRankRequiredXP", compRank.getNextRequiredExp());
		tag.setString("playerUUID", uuid.toString());
		tag.setInteger("prestigeLevel", prestige.get());

		tag.setInteger("achievementCount", achievements.size());
		Util.enumerate(achievements, (achievement, i) -> tag.setInteger("achievement_" + i, achievement));

		PlayerGroup groupGet = group.get();
		if (groupGet != null) {
			groupGet.writeTag(tag);
		}

		if (writePunishments) {
			int numPunishments = 0;

			LocalDateTime now = Util.utcNow();
			for (Punishment punishment : punishments) {
				if (now.isAfter(punishment.end())) {
					continue;
				}

				FDSTagCompound punishmentTag = new FDSTagCompound("pun" + numPunishments++);
				punishment.writeTag(punishmentTag, now);
				tag.setTagCompound(punishmentTag.getTag(), punishmentTag);
			}

			tag.setInteger("punishSize", numPunishments);
		}

		tag.setInteger("maxInv", 1000);
		tag.setString("discordUserId", "");
		tag.setBoolean("enableNotification_FriendRequest", true);
		tag.setBoolean("enableNotification_FriendPoke", true);
		tag.setBoolean("enableNotification_TradeRequest", true);
		tag.setBoolean("enableProfileViewing", true);
		tag.setBoolean("enableTradeRequests", true);
		tag.setBoolean("enableFriendRequests", true);
		tag.setBoolean("twoFactorEnable", false);
		tag.setBoolean("isSupporter", false);
		tag.setBoolean("enableCape", false);
		tag.setBoolean("hasparty", false);
	}

	public void writeFriendsTag(FDSTagCompound tag) {
		tag.setStringArrayList("friends", collectUsernames(friends));
		tag.setStringArrayList("pending", collectUsernames(friendRequests));
	}

	private static ArrayList<String> collectUsernames(Set<UUID> uuids) {
		return uuids.stream().map(DataHandler.instance()::getPlayerUsername).filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
	}
}
