package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GoalComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> list = new ArrayList<>();

        switch (strings.length) {
            case 1:
                Goal.goals().forEach(goal -> list.add(goal.key));
                break;
            case 2:
                list.add("on");
                list.add("off");
        }

        return list;
    }
}
