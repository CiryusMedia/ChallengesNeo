package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ChallengeGuiItems extends GeneralGuiItems {

    GuiItemStack challengeFillerItem = new GuiItemStack(Material.RED_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack challengeLineFillerItem = new GuiItemStack(Material.RED_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);

    //Challenges
    GuiItemStack randomChallenges = new GuiItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, ChatColor.LIGHT_PURPLE + "Random Challenges");

    GuiItemStack inventorySync = Challenge.INVENTORY_SYNC.menuItem;


    Material[] randomChallengeMaterials = new Material[Arrays.stream(Material.values()).filter(Material::isItem).toArray().length];

    default void initChallengeGuiItems() {

        List<Material> materials = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isItem())
                materials.add(material);
        }

        for (int i = 0; i < randomChallengeMaterials.length; i++) {
            randomChallengeMaterials[i] = materials.get(i);
        }
    }

}
