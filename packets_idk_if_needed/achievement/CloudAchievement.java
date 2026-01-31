package dev.vuis.cccr.cloud.packets.achievement;

import com.ferullogaming.countercraft.game.IGame;

public class CloudAchievement {
    private int id;
    private String name;
    private String description;
    private boolean hasReward = true;

    public CloudAchievement(int givenID, String givenName, String givenDesc) {
        this.id = givenID;
        this.name = givenName;
        this.description = givenDesc;
        AchievementManager.ACHIEVEMENTS.put(this.id, this);
    }

    public CloudAchievement disableReward() {
        this.hasReward = false;
        return this;
    }
}