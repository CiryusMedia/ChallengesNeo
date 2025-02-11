package com.ciryusmedia.challengenetwork.challengespluginneo.challenges.synched;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventorySync extends ChallengeOld {

    public InventorySync() {
        super("InventorySync", "Inventory Sync", "sync", "inventorySync");
    }

    @Override
    public void createMenuItem() {
        menuItem = new ItemStack(Material.CRAFTING_TABLE);
        updateMenuItem();
    }

    @Override
    public void updateItemDescription() {
        List<String> itemDescription = new ArrayList<>();
        itemDescription.add("Gives every player the same inventory");
        itemDescription.add("This means that every item in your");
        itemDescription.add("inventories is identical");
        itemDescription.add("");

        this.itemDescription = itemDescription;
    }
}
