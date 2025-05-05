package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GoalCommand implements CommandExecutor {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        // command structure: goal ender_dragon enable
        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "kill_ender_dragon": {
                handleGoal(commandSender, Goal.KILL_ENDER_DRAGON, args);
            }
            case "get_all_advancements": {
                handleGoal(commandSender, Goal.GET_ALL_ADVANCEMENTS, args);
            }
            case "player_death": {
                handleGoal(commandSender, Goal.PLAYER_DEATH, args);
            }
        }

        return false;
    }

    public void handleGoal(CommandSender commandSender, Goal goal, String[] args) {
        if (args.length == 1) {
            commandSender.sendMessage(goal.displayName + " is currently " + (goal.isEnabled() ? "on" : "off"));
            return;
        }
        if (args[1].equalsIgnoreCase("on")) {
            Goal.enableGoal(goal);
        } else if (args[0].equalsIgnoreCase("off")) {
            Goal.disableGoal(goal);
        }
    }
}
