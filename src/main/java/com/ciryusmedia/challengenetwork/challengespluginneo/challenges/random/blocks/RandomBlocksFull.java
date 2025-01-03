package com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomBlocksFull extends Challenge {

    public RandomBlocksFull() {
        super("RandomBlocksFull", "Random Blocks Full", "random", "randomBlocks");
    }

    @Override
    public void createMenuItem() {
        menuItem = new ItemStack(Material.STONE);
        updateMenuItem();
    }

    @Override
    public void updateItemDescription() {
        List<String> itemDescription = new ArrayList<>();
        itemDescription.add("Makes every Blockdrop random,");
        itemDescription.add("not with a pattern, just straight up random");
        itemDescription.add("");

        this.itemDescription = itemDescription;
    }

}
