package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public interface GoalsGuiItems extends GeneralGuiItems {

    GuiItemStack goalFillerItem = new GuiItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack goalLineFillerItem = new GuiItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);
    GuiItemStack goalExitWarning = new GuiItemStack(Material.BARRIER, ChatColor.RED + "Exit", CMD_EXIT, List.of(ChatColor.RED + "WARNING: There aren't any goals active!"));

}
