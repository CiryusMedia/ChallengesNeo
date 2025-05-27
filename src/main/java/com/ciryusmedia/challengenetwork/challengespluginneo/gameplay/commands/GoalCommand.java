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

public class GoalCommand extends AGuiCompatibleCommand implements Texts {

    @Override
    public void handleArgs1(CommandSender commandSender, String[] args) {
        Goal goal = Goal.getGoal(args[0]);
        if (goal == null) {
            commandSender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        commandSender.sendMessage(PREFIX + goal.displayName + " is " +
                (goal.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }

    @Override
    public void handleArgs2(CommandSender commandSender, String[] args) {
        Goal goal = Goal.getGoal(args[0]);
        if (goal == null) {
            commandSender.sendMessage(PREFIX + INVALID_CHALLENGE);
            return;
        }
        if (args.length >= 2) {
            handleGoal(commandSender, goal, args[1]);
        }
    }

    public void handleGoal(CommandSender commandSender, Goal goal, String argument) {
        int arg = getIntegerFromArg(argument);
        if (arg == -1) {
            commandSender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        if (arg == 1) {
            List<Goal> goalsWithSameType = Goal.goals(goal.type);
            if (!goalsWithSameType.isEmpty()) {
                goalsWithSameType.forEach(g -> g.setEnabled(false));
            }
        }

        goal.setEnabled(arg == 1);

        commandSender.sendMessage(PREFIX + goal.displayName + " is now " +
                (goal.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }

    public GoalCommand() {
        super(ChallengesPluginNeo.goalsGUI);
    }
}
