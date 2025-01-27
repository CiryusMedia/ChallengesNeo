package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestCommand implements CommandExecutor {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();

    //DO NOT CLEAR IMPORTS! Imports are used in the unused code, which should be kept, even after the test is passed
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        //Test for random potions

//        ItemStack testItem = new ItemStack(Material.TIPPED_ARROW);
//        PotionMeta testPotionMeta = RandomisationOutsourcing.getRandomPotionMeta(testItem);
//
//        testItem.setItemMeta(testPotionMeta);
//
//        if (commandSender instanceof Player p) {
//            p.getInventory().addItem(testItem);
//        }

        //Test for active challenge
//        commandSender.sendMessage(String.valueOf(instance.getCoo().RANDOM_BLOCKS_LOOTTABLE.isEnabled()));

        //Create empty loottable files :)
//        Material[] allBlocks = Arrays.stream(Material.values()).filter(Material::isBlock).toArray(Material[]::new);
//        EntityType[] allEntities = EntityType.values();
//        File config1F = new File(instance.getDataFolder(), "randomblocksloottabledefaults.yml");
//        YamlConfiguration config1 = YamlConfiguration.loadConfiguration(config1F);
//        for (Material allBlock : allBlocks) {
//
//            config1.set(allBlock.name(), "");
//            try {
//                config1.save(config1F);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        config1F = new File(instance.getDataFolder(), "randommobsloottabledefaults.yml");
//        config1 = YamlConfiguration.loadConfiguration(config1F);
//
//        for (EntityType allEntity : allEntities) {
//            config1.set(allEntity.name(), "");
//            try {
//                config1.save(config1F);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        commandSender.sendMessage("There is nothing to test");

        return true;
    }

}
