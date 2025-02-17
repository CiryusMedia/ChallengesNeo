package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ChallengeGuiItems extends GeneralGuiItems {

    ItemStack challengeFillerItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    ItemStack challengeLineFillerItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);

    //Challenges
    ItemStack randomChallenges = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);

    ItemStack inventorySync = Challenge.INVENTORY_SYNC.menuItem;


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

        //Filler item
        ItemMeta fillerMeta = challengeFillerItem.getItemMeta();
        fillerMeta.setDisplayName(" ");
        fillerMeta.setCustomModelData(1); //Make the Item invisible in default resourcepack config

        challengeFillerItem.setItemMeta(fillerMeta);

        //Line filler Item
        ItemMeta lineFillerMeta = challengeLineFillerItem.getItemMeta();
        lineFillerMeta.setDisplayName(" ");
        lineFillerMeta.setCustomModelData(3); //Make the Item fill a row of 9 slots

        challengeLineFillerItem.setItemMeta(lineFillerMeta);

        //Random Item
        ItemMeta randomChallengesMeta = randomChallenges.getItemMeta();
        randomChallengesMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Random Challenges");

        randomChallenges.setItemMeta(randomChallengesMeta);
    }

}
