package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ConfigPaths;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor, ConfigPaths {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //Kicking everyone
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Server Reset"));
        LOGGER.log(ChatColor.RED + "Resetting Server");

        //Setting timer
        plugin.getTimer().setRunning(false);
        plugin.getTimer().setTime(0);

        //Setting config
        plugin.getConfig().set(SYSTEM_IS_RESET, true);
        plugin.saveConfig();

        //Deleting/clearing loottable config files
        plugin.getFileLoader().getRandomBlocksLoottableConfigFile().delete();
        plugin.getFileLoader().getRandomMobsLoottableConfigFile().delete();

//        Bukkit.spigot().restart(); //Spigot restart is questionable
        Bukkit.shutdown();
        return true;
    }
}
