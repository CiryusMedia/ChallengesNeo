package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ResetCommand implements CommandExecutor {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();
    Plugin plugin = ChallengesPluginNeo.getPlugin(ChallengesPluginNeo.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //Kicking everyone
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Server Reset"));
        instance.log(ChatColor.RED + "Resetting Server", Debuglevel.LEVEL_0);

        //Setting timer
        instance.getTimer().setRunning(false);
        instance.getTimer().setTime(0);

        //Setting config
        plugin.getConfig().set("isReset", true);
        plugin.saveConfig();

        //Deleting/clearing loottable config files
        instance.getRandomBlocksLoottableConfigFile().delete();
        instance.getRandomMobsLoottableConfigFile().delete();

//        Bukkit.spigot().restart(); //Spigot restart is questionable
        Bukkit.shutdown();
        return true;
    }
}
