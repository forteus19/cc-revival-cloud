package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import com.ferullogaming.countercraft.item.gun.ItemGun;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class CloudItemGun extends CloudItem {
    public CloudItemGun(int ccID, int mcID, String itemName) {
        super(ccID, mcID, itemName);
        this.setFilterIT("gun");
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        super.addInformation(par1, par2);
        if (this.itemCollection != null) {
            par2.add("");
            par2.add(EnumChatFormatting.BOLD + "Collection");
            par2.add(EnumChatFormatting.GRAY + this.itemCollection);
        }

        par2.add(EnumChatFormatting.BOLD + "Author");
        par2.add(EnumChatFormatting.GRAY + this.getAuthor());
        if (this.hasStickers(par1.getItemStack())) {
            par2.add(EnumChatFormatting.BOLD + "Stickers");

            for(int i = 0; i < 3; ++i) {
                if (ItemGun.getStickerInPosition(par1.getItemStack(), i) != null && !ItemGun.getStickerInPosition(par1.getItemStack(), i).equalsIgnoreCase("none")) {
                    String stickerName = ItemGun.getStickerInPosition(par1.getItemStack(), i);
                    CloudItem[] arr$ = itemList;
                    int len$ = arr$.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        CloudItem item = arr$[i$];
                        if (item instanceof CloudItemSticker) {
                            CloudItemSticker theSticker = (CloudItemSticker)item;
                            if (theSticker.getStickerTextureName().equals(stickerName)) {
                                stickerName = theSticker.value.chatColor + theSticker.getDisplayName().replace("Sticker", "").replace(" ", "");
                            }
                        }
                    }

                    par2.add(stickerName);
                }
            }
        }

    }

    public boolean hasStickers(ItemStack itemstack) {
        for(int i = 0; i < 3; ++i) {
            ItemGun theGun = (ItemGun)itemstack.func_77973_b();
            if (ItemGun.getStickerInPosition(itemstack, i) != null && !ItemGun.getStickerInPosition(itemstack, i).equalsIgnoreCase("none")) {
                return true;
            }
        }

        return false;
    }
}
