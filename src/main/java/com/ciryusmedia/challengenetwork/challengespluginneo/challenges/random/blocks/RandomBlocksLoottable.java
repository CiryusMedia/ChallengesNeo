package com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomBlocksLoottable extends Challenge {

    public RandomBlocksLoottable() {
        super("RandomBlocksLoottable", "Random Blocks Loottable", "random", "randomBlocks");
    }

    @Override
    public void createMenuItem() {
        menuItem = new ItemStack(Material.GRASS_BLOCK);
        updateMenuItem();
    }

    @Override
    public void updateItemDescription() {
        List<String> itemDescription = new ArrayList<>();
        itemDescription.add("Makes every Blockdrop random, but still");
        itemDescription.add("with a pattern, making it predictably random");
        itemDescription.add("");

        this.itemDescription = itemDescription;
    }

}
