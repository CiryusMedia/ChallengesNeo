package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            player.sendMessage(Texts.PREFIX + ChatColor.GREEN + "Healed!");
        } else {
            commandSender.sendMessage(Texts.PREFIX + Texts.NOT_PLAYER);
        }

        return true;
    }
}
