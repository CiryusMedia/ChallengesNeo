package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.commands.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DebugComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();

        if (strings.length == 1) {
            list.add("0");
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");

            list.add("99");
        }

        return list;
    }
}
