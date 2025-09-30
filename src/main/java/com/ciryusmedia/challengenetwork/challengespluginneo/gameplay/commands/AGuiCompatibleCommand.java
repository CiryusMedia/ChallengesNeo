package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AGuiCompatibleCommand implements CommandExecutor, Texts {

    protected final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    AGUIListener guiListener;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        // command structure: comp coop enable|true
        if (args.length == 0) {
            if (commandSender instanceof Player player) {
                player.openInventory(guiListener.getInventory());
                return true;
            } else {
                commandSender.sendMessage(PREFIX + NOT_ENOUGH_ARGUMENTS);
                return false;
            }
        }

        if (args.length == 1) handleArgs1(commandSender, args);

        if (args.length == 2) handleArgs2(commandSender, args);

        return true;
    }

    public int getIntegerFromArg(String arg) { //Done with a nullable Integer, so that there are more than 2 possibilities
        if (arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("off")) {
            return 0;
        } else if (arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("on")) {
            return 1;
        } else {
            return -1;
        }
    }

    protected void handleArgs1(@NotNull CommandSender commandSender, @NotNull String[] args) {
    }

    protected void handleArgs2(@NotNull CommandSender commandSender, @NotNull String[] args) {
    }

    public AGuiCompatibleCommand(AGUIListener guiListener) {
        this.guiListener = guiListener;
    }

}
