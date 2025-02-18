package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

@SuppressWarnings("deprecation")
public enum Gui {

    //<editor-fold desc="Timer" defaultstate="collapsed">

    //</editor-fold>

    //<editor-fold desc="Challenge" defaultstate="collapsed">
    ;
    //</editor-fold>

    public final Inventory inv;

    public final ItemStack fillerItem;
    public final ItemStack lineFillerItem;

    public Collection<GuiItem> options;
    public final int spacing;

    private void fillInventory() {
        for (int currentSlot = 0; currentSlot < inv.getSize(); currentSlot++) {
            inv.setItem(currentSlot, fillerItem);
            if (currentSlot % 9 == 0) {
                inv.setItem(currentSlot, lineFillerItem);
            }
        }


    }

    public void updateInventory() {

    }

    public void setOptions(Collection<GuiItem> options) {
        this.options = options;
    }

    Gui(String title, int size, ItemStack fillerItem, ItemStack lineFillerItem, Collection<GuiItem> options, int spacing) {
        this.inv = Bukkit.createInventory(null, size, title);
        this.fillerItem = fillerItem;
        this.lineFillerItem = lineFillerItem;
        this.options = options;
        this.spacing = spacing;

        fillInventory();
    }
}
