package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //Kicking everyone
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Server Reset"));
        DEBUGGER.log(ChatColor.RED + "Resetting Server", DebugLevel.LEVEL_0);

        //Setting timer
        plugin.getTimer().setRunning(false);
        plugin.getTimer().setTime(0);

        //Setting config
        plugin.getConfig().set("isReset", true);
        plugin.saveConfig();

        //Deleting/clearing loottable config files
        plugin.getRandomBlocksLoottableConfigFile().delete();
        plugin.getRandomMobsLoottableConfigFile().delete();

//        Bukkit.spigot().restart(); //Spigot restart is questionable
        Bukkit.shutdown();
        return true;
    }
}
