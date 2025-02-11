package com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengesOutsourcing;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface RandomChallengeGuiItems extends ChallengeGuiItems {

    ItemStack randomBlocksLoottable = ChallengesOutsourcing.RANDOM_BLOCKS_LOOTTABLE.getMenuItem();
    ItemStack randomBlocksFull = ChallengesOutsourcing.RANDOM_BLOCKS_FULL.getMenuItem();
    ItemStack randomMobsLoottable = ChallengesOutsourcing.RANDOM_MOBS_LOOTTABLE.getMenuItem();
    ItemStack randomMobsFull = ChallengesOutsourcing.RANDOM_MOBS_FULL.getMenuItem();

    default void initRandomChallengeGuiItems() {

        //Filler item
        ItemMeta fillerMeta = challengeFillerItem.getItemMeta();
        fillerMeta.setDisplayName(" ");
        fillerMeta.setCustomModelData(1); //Make the Item invisible in default resourcepack config

        challengeFillerItem.setItemMeta(fillerMeta);

    }

    default void updateRandomChallengeGuiItems() {
        ChallengesOutsourcing.checkInitialized();
        ChallengesOutsourcing.getCHALLENGES().forEach(ChallengeOld::updateMenuItem);
    }

}
