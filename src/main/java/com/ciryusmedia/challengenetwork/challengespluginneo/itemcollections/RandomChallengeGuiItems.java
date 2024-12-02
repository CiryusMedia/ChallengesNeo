package com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface RandomChallengeGuiItems extends ChallengeGuiItems {

    ItemStack randomBlocksLoottable = coo.RANDOM_BLOCKS_LOOTTABLE.getMenuItem();
    ItemStack randomBlocksFull = coo.RANDOM_BLOCKS_FULL.getMenuItem();
    ItemStack randomMobsLoottable = coo.RANDOM_MOBS_LOOTTABLE.getMenuItem();
    ItemStack randomMobsFull = coo.RANDOM_MOBS_FULL.getMenuItem();

    default void initRandomChallengeGuiItems() {

        //Filler item
        ItemMeta fillerMeta = challengeFillerItem.getItemMeta();
        fillerMeta.setDisplayName(" ");
        fillerMeta.setCustomModelData(1); //Make the Item invisible in default resourcepack config

        challengeFillerItem.setItemMeta(fillerMeta);

    }

    default void updateRandomChallengeGuiItems() {
        coo.getCHALLENGES().forEach(Challenge::updateMenuItem);
    }

}
