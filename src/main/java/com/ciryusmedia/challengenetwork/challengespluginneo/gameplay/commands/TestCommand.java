package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    //DO NOT CLEAR IMPORTS! Imports are used in the unused code, which should be kept, even after the test is passed
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
//        //Test for random potions
//
//        ItemStack testItem = new ItemStack(Material.TIPPED_ARROW);
//        PotionMeta testPotionMeta = plugin.getRro().getRandomPotionMeta(testItem);
//
//        testItem.setItemMeta(testPotionMeta);
//
//        if (commandSender instanceof Player p) {
//            p.getInventory().addItem(testItem);
//        }
//
//        //Test for active challenge
//        commandSender.sendMessage(String.valueOf(plugin.getCho().RANDOM_BLOCKS_LOOTTABLE.isEnabled()));
//
//        //Create empty loottable files :)
//        Material[] allBlocks = Arrays.stream(Material.values()).filter(Material::isBlock).toArray(Material[]::new);
//        EntityType[] allEntities = EntityType.values();
//        File config1F = new File(plugin.getDataFolder(), "randomblocksloottabledefaults.yml");
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
//        config1F = new File(plugin.getDataFolder(), "randommobsloottabledefaults.yml");
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
