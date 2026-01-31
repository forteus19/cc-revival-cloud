package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.ArrayList;

public class CloudItemCase extends CloudItem {
    public final int keyId;
    public ArrayList<CaseContent> content = new ArrayList();
    public ArrayList<CaseContent> contentDisplay = new ArrayList();

    public CloudItemCase(int ccID, int mcID, String itemName, int par4KeyId) {
        super(ccID, mcID, itemName);
        this.keyId = par4KeyId;
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        super.addInformation(par1, par2);
        par2.add("");
        par2.add("Try your luck, open");
        par2.add("to obtain new skins!");
    }

    public CloudItemCase addContent(CloudItem par1, int par2Weight) {
        this.contentDisplay.add(new CaseContent(par1.getID()));

        for(int i = 0; i < par2Weight; ++i) {
            this.content.add(new CaseContent(par1.getID()));
        }

        return this;
    }

    public CloudItemCase addContent(int par1ID, int par2Weight) {
        this.contentDisplay.add(new CaseContent(par1ID));

        for(int i = 0; i < par2Weight; ++i) {
            this.content.add(new CaseContent(par1ID));
        }

        return this;
    }

    public CloudItem getKey() {
        return CloudItem.getCloudItemFromId(this.keyId);
    }
}
