package com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomMobsLoottable extends ChallengeOld {

    public RandomMobsLoottable() {
        super("RandomMobsLoottable", "Random Mobs Loottable", "random", "randomMobs");
    }

    @Override
    public void createMenuItem() {
        menuItem = new ItemStack(Material.SNIFFER_SPAWN_EGG);
        updateMenuItem();
    }

    @Override
    public void updateItemDescription() {
        List<String> itemDescription = new ArrayList<>();
        itemDescription.add("Makes every Mobdrop random, but still");
        itemDescription.add("with a pattern, making it predictably random");
        itemDescription.add("");

        this.itemDescription = itemDescription;
    }
}
