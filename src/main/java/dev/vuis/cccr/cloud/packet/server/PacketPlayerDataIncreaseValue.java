package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.achievement.CloudAchievement;
import dev.vuis.cccr.cloud.achievement.CloudAchievements;
import dev.vuis.cccr.cloud.data.CloudStats;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.Exp;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketPlayerDataIncreaseValue extends G_TcpPacketCustomPayload {
	private String username;
	private String value;
	private int amount;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		username = readString(in);
		value = readString(in);
		amount = in.readInt();
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.kickIfServer(connectionHandler)) {
			return;
		}
		if (!ServerStatus.REGION.containsKey(connectionHandler.getConnectionUsername())) {
			return;
		}

		PlayerData playerData = DataHandler.instance().getPlayerData(username);
		if (playerData == null) {
			return;
		}

		Cloud.instanceLog("INCREASE VALUE %s %s %d", username, value, amount);

		playerData.dirty.mark();

		int expGain = 0;
		switch (value) {
			case "achievement" -> {
				CloudAchievement achievement = CloudAchievements.getById(amount);
				if (achievement != null) {
					achievement.handleUnlock(playerData, connectionHandler);
				}
				return;
			}
			case "deaths" -> {
				CloudAchievement deathsAch = CloudAchievements.getDeathsAchievement(playerData.deaths.addAndGet(amount));
				if (deathsAch != null) {
					deathsAch.handleUnlock(playerData, connectionHandler);
				}
				expGain = Exp.DEATH;
			}
			case "emeralds" -> {
				playerData.emeralds.addAndGet(amount);
			}
			case "firstblood" -> {
				playerData.firstBloods.addAndGet(amount);
				expGain = Exp.FIRST_BLOOD;
			}
			case "games" -> {
				playerData.games.addAndGet(amount);
				expGain = Exp.MATCH_PARTICIPATE;
			}
			case "kills" -> {
				CloudAchievement killsAch = CloudAchievements.getKillsAchievement(playerData.kills.addAndGet(amount));
				if (killsAch != null) {
					killsAch.handleUnlock(playerData, connectionHandler);
				}
				CloudStats.incrementPlayerScore(playerData.username.get());
				expGain = Exp.KILL;
			}
			case "trophies" -> {
				playerData.trophies.addAndGet(amount);
				expGain = Exp.TROPHY;
			}
		}

		expGain *= amount;
		if (expGain != 0) {
			Exp.handleExpGain(connectionHandler, playerData, expGain);
		}
	}
}
