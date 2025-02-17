package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface RandomChallengeGuiItems extends ChallengeGuiItems {

    ItemStack randomBlocksLoottable = Challenge.RANDOM_BLOCKS_LOOTTABLE.menuItem;
    ItemStack randomBlocksFull = Challenge.RANDOM_BLOCKS_FULL.menuItem;
    ItemStack randomMobsLoottable = Challenge.RANDOM_MOBS_LOOTTABLE.menuItem;
    ItemStack randomMobsFull = Challenge.RANDOM_MOBS_FULL.menuItem;

    default void initRandomChallengeGuiItems() {

        //Filler item
        ItemMeta fillerMeta = challengeFillerItem.getItemMeta();
        fillerMeta.setDisplayName(" ");
        fillerMeta.setCustomModelData(1); //Make the Item invisible in default resourcepack config

        challengeFillerItem.setItemMeta(fillerMeta);

    }

    default void updateRandomChallengeGuiItems() {
        Challenge.challenges.stream().forEach(Challenge::updateMenuItem);
    }

}
