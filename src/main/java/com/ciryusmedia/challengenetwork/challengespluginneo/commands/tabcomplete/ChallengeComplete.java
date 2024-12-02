package com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("random");
            return list;
        }

        if (strings.length == 2) {
            List<String> list = new ArrayList<>();
            list.add("randomblocksloottable");
            list.add("randomblocksfull");
            list.add("randommobsloottable");
            list.add("randommobsfull");
            return list;
        }

        if (strings.length == 3) {
            List<String> list = new ArrayList<>();
            list.add("true");
            list.add("false");
            list.add("on");
            list.add("off");
            return list;
        }

        return null;
    }

}
