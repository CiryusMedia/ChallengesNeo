package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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

        commandSender.sendMessage("There is nothing to test");

        return true;
    }

}
