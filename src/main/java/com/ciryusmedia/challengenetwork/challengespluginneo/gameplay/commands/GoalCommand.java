package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.InventoryCollection;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GoalCommand implements CommandExecutor, Texts {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        // command structure: goal ender_dragon enable
        if (args.length == 0) {
            if (commandSender instanceof Player player){
                player.openInventory(InventoryCollection.goalsGUI);
                return true;
            } else {
                commandSender.sendMessage(PREFIX + NOT_ENOUGH_ARGUMENTS);
                return false;
            }
        }

        Goal goal = Goal.getGoal(args[0]);
        if (goal == null) {
            commandSender.sendMessage(PREFIX + INVALID_CHALLENGE);
            return false;
        }
        if (args.length >= 2) {
            handleGoal(commandSender, goal, args[1]);
            return true;
        }
        commandSender.sendMessage(PREFIX + goal.displayName + " is " +
                (goal.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );

        return false;
    }

    public void handleGoal(CommandSender commandSender, Goal goal, String argument) {
        boolean arg;
        if (argument.equalsIgnoreCase("true") || argument.equalsIgnoreCase("on")) {
            arg = true;
        } else if (argument.equalsIgnoreCase("false") || argument.equalsIgnoreCase("off")) {
            arg = false;
        } else {
            commandSender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        if (arg) {
            List<Goal> goalsWithSameType = Goal.goals(goal.type);
            if (!goalsWithSameType.isEmpty()) {
                goalsWithSameType.forEach(g -> g.setEnabled(false));
            }
        }

        goal.setEnabled(arg);

        commandSender.sendMessage(PREFIX + goal.displayName + " is now " +
                (goal.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }
}
