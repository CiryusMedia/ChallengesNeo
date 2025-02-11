package com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomMobsFull extends ChallengeOld {

    public RandomMobsFull() {
        super("RandomMobsFull", "Random Mobs Full", "random", "randomMobs");
    }

    @Override
    public void createMenuItem() {
        menuItem = new ItemStack(Material.PUFFERFISH);
        updateMenuItem();
    }

    @Override
    public void updateItemDescription() {
        List<String> itemDescription = new ArrayList<>();
        itemDescription.add("Makes every Mobdrop random,");
        itemDescription.add("not with a pattern, just straight up random");
        itemDescription.add("");

        this.itemDescription = itemDescription;
    }
}
