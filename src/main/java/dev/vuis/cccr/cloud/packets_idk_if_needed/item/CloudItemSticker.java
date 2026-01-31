package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.ArrayList;

public class CloudItemSticker extends CloudItem {
    public String stickerTexture = "none";

    public CloudItemSticker(int par1, int par2, String par3) {
        super(par1, par2, par3);
        this.setFilterIT("sticker");
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        super.addInformation(par1, par2);
    }

    public String getStickerTextureName() {
        return this.stickerTexture;
    }

    public CloudItemSticker setStickerTextureName(String givenTextureName) {
        this.stickerTexture = givenTextureName;
        return this;
    }
}