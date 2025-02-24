package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum GuiOptions {
    CHALLENGE_OPTIONS(Arrays.stream(new ItemStack[]{
            GuiItem.RANDOM_CHALLENGES.itemStack,
            Challenge.INVENTORY_SYNC.menuItem
    }).toList())
    ;

    public final List<ItemStack> items;

    GuiOptions(List<ItemStack> items) {
        this.items = items;
    }
}
