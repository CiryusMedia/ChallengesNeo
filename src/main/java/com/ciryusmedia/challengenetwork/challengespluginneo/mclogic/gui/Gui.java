package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public enum Gui {

    //<editor-fold desc="Timer" defaultstate="collapsed">

    //</editor-fold>

    //<editor-fold desc="Challenge" defaultstate="collapsed">
    ;
    //</editor-fold>

    public final Inventory inv;

    public final ItemStack fillerItem;
    public final ItemStack lineFillerItem;

    private void fillInventory() {
        for (int currentSlot = 0; currentSlot < inv.getSize(); currentSlot++) {
            inv.setItem(currentSlot, fillerItem);
            if (currentSlot % 9 == 0) {
                inv.setItem(currentSlot, lineFillerItem);
            }
        }
    }

    Gui(String title, int size, ItemStack fillerItem, ItemStack lineFillerItem) {
        this(Bukkit.createInventory(null, size, title), fillerItem, lineFillerItem);
    }

    Gui(Inventory inv, ItemStack fillerItem, ItemStack lineFillerItem) {
        this.inv = inv;
        this.fillerItem = fillerItem;
        this.lineFillerItem = lineFillerItem;

        fillInventory();
    }
}
