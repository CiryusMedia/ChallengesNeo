package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.Material;

public interface CoopGuiItems extends GeneralGuiItems {

    GuiItemStack coopFillerItem = new GuiItemStack(Material.LIME_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack coopLineFillerItem = new GuiItemStack(Material.LIME_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);


}
