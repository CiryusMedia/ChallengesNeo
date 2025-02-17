package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugCommand implements CommandExecutor {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage(Texts.PREFIX + " The current debug level is " + ChatColor.YELLOW + plugin.getConfig().getInt("DebugLevel"));
            return true;
        } else {
            try {
                plugin.getConfig().set("DebugLevel", Integer.parseInt(strings[0]));

                DEBUGGER.log("Debug level successfully set", DebugLevel.LEVEL_3);
                commandSender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Debug level has been set to " + ChatColor.AQUA + plugin.getConfig().getInt("DebugLevel"));
            } catch (NumberFormatException e) {
                DEBUGGER.log("Debug level is not a number", DebugLevel.LEVEL_3);
                commandSender.sendMessage(Texts.PREFIX + ChatColor.RED + "Time must be a number!");
            }
            plugin.saveConfig();
        }

        return true;
    }
}
