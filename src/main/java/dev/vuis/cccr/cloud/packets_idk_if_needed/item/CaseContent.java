package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

public class CaseContent {
    private int cloudItemID;

    public CaseContent(int var1) {
        this.cloudItemID = var1;
    }

    public CloudItem getCloudItem() {
        return CloudItem.itemList[this.cloudItemID];
    }
}
