package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugCommand implements CommandExecutor {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage(Texts.PREFIX + " The current debug level is " + ChatColor.YELLOW + plugin.getConfig().getInt("DebugLevel"));
            return true;
        } else {
            try {
                plugin.getConfig().set("DebugLevel", Integer.parseInt(strings[0]));

                plugin.log("Debug level successfully set", DebugLevelOld.LEVEL_3);
                commandSender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Debug level has been set to " + ChatColor.AQUA + plugin.getConfig().getInt("DebugLevel"));
            } catch (NumberFormatException e) {
                plugin.log("Debug level is not a number", DebugLevelOld.LEVEL_3);
                commandSender.sendMessage(Texts.PREFIX + ChatColor.RED + "Time must be a number!");
            }
            plugin.saveConfig();
        }

        return true;
    }
}
