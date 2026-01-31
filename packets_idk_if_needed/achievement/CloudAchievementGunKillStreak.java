package dev.vuis.cccr.cloud.packets.achievement;

import com.ferullogaming.countercraft.damagesource.DamageSourceBullet;
import com.ferullogaming.countercraft.game.GameHelper;
import com.ferullogaming.countercraft.game.IGame;
import com.ferullogaming.countercraft.item.gun.ItemGun;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class CloudAchievementGunKillStreak extends CloudAchievement {
    private final String gunID;
    private final boolean headshotOnly;
    private final int count;
    private final List<ItemGun> guns;

    public CloudAchievementGunKillStreak(int givenID, String givenName, String givenDesc, String gunID, boolean hsOnly, int count, ItemGun[] guns) {
        super(givenID, givenName, givenDesc);
        this.gunID = gunID;
        this.headshotOnly = hsOnly;
        this.count = count;
        this.guns = Arrays.asList(guns);
    }

    public boolean tryUnlockDeath(EntityPlayer par1, DamageSource par2, IGame par3) {
        if (par2.func_76364_f() instanceof EntityPlayer) {
            if (par2 instanceof DamageSourceBullet) {
                DamageSourceBullet bsource = (DamageSourceBullet)par2;
                if (this.headshotOnly && !bsource.isHeadshot()) {
                    return false;
                }
            }

            String achTag = "achievement_ks_" + this.gunID + "_" + this.headshotOnly;
            EntityPlayer killer = (EntityPlayer)par2.func_76364_f();
            if (killer.func_70694_bm() != null && killer.func_70694_bm().func_77973_b() instanceof ItemGun) {
                ItemGun gun = (ItemGun)killer.func_70694_bm().func_77973_b();
                if (this.guns.contains(gun)) {
                    GameHelper.increasePlayerGameValue(par3, killer.field_71092_bJ, achTag);
                    return GameHelper.getPlayerGameValue(par3, killer.field_71092_bJ, achTag) == this.count;
                }
            }
        }

        return false;
    }
}