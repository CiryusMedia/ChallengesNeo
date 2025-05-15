package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;

public interface RandomChallengeGuiItems extends ChallengeGuiItems {

    GuiItemStack randomBlocksLoottable = Challenge.RANDOM_BLOCKS_LOOTTABLE.menuItem;
    GuiItemStack randomBlocksFull = Challenge.RANDOM_BLOCKS_FULL.menuItem;
    GuiItemStack randomMobsLoottable = Challenge.RANDOM_MOBS_LOOTTABLE.menuItem;
    GuiItemStack randomMobsFull = Challenge.RANDOM_MOBS_FULL.menuItem;

    default void updateRandomChallengeGuiItems() {
        Challenge.challenges.stream().forEach(Challenge::updateItem);
    }

}
