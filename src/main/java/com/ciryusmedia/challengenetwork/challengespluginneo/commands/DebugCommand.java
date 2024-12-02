package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class DebugCommand implements CommandExecutor {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();
    Plugin plugin = ChallengesPluginNeo.getPlugin(ChallengesPluginNeo.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage(Texts.PREFIX + " The current debug level is " + ChatColor.YELLOW + plugin.getConfig().getInt("DebugLevel"));
            return true;
        } else {
            try {
                plugin.getConfig().set("DebugLevel", Integer.parseInt(strings[0]));

                instance.log("Debug level successfully set", Debuglevel.LEVEL_3);
                commandSender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Debug level has been set to " + ChatColor.AQUA + plugin.getConfig().getInt("DebugLevel"));
            } catch (NumberFormatException e) {
                instance.log("Debug level is not a number", Debuglevel.LEVEL_3);
                commandSender.sendMessage(Texts.PREFIX + ChatColor.RED + "Time must be a number!");
            }
            plugin.saveConfig();
        }

        return true;
    }
}
