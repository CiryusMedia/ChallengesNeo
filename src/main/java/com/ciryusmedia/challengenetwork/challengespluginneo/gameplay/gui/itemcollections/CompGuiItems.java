package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.Material;

public interface CompGuiItems extends GeneralGuiItems {

    GuiItemStack compFillerItem = new GuiItemStack(Material.LIME_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack compLineFillerItem = new GuiItemStack(Material.LIME_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);


}
