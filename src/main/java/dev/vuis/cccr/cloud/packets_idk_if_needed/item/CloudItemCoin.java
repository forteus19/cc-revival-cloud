package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.ArrayList;

public class CloudItemCoin extends CloudItem {
    public CloudItemCoin(int ccID, int mcID, String itemName) {
        super(ccID, mcID, itemName);
        this.setFilterIT("coin");
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        super.addInformation(par1, par2);
    }
}
