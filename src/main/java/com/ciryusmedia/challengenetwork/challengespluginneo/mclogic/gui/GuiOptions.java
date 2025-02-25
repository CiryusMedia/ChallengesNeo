package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum GuiOptions {
    CHALLENGE_OPTIONS(new ItemStack[]{
            GuiItem.RANDOM_CHALLENGES.itemStack,
            Challenge.INVENTORY_SYNC.menuItem
    }),
    RANDOM_CHALLENGES_OPTIONS(new ItemStack[]{
            Challenge.RANDOM_BLOCKS_FULL.menuItem,
            Challenge.RANDOM_BLOCKS_LOOTTABLE.menuItem,
            GuiItem.CHALLENGE_FILLER.itemStack,
            GuiItem.CHALLENGE_FILLER.itemStack,
            GuiItem.CHALLENGE_FILLER.itemStack,
            Challenge.RANDOM_MOBS_FULL.menuItem,
            Challenge.RANDOM_MOBS_LOOTTABLE.menuItem
    })
    ;

    public final List<ItemStack> items;

    GuiOptions(ItemStack[] items) {
        this(Arrays.stream(items).toList());
    }

    GuiOptions(List<ItemStack> items) {
        this.items = items;
    }
}
