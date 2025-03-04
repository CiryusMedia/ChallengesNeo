package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.guienumconcept;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public enum Gui {

    //<editor-fold desc="Timer" defaultstate="collapsed">
    TIMER_GUI("Timer", 3, GuiItem.TIMER_FILLER, GuiItem.TIMER_LINE_FILLER, GuiOptions.TIMER_OPTIONS, 1),
    //</editor-fold>

    //<editor-fold desc="Challenge" defaultstate="collapsed">
    CHALLENGE_GUI("Challenges", 3, GuiItem.CHALLENGE_FILLER, GuiItem.CHALLENGE_LINE_FILLER, GuiOptions.CHALLENGE_OPTIONS, 1),
    RANDOM_CHALLENGES("Random Challenges", 3, GuiItem.CHALLENGE_FILLER, GuiItem.CHALLENGE_LINE_FILLER, GuiOptions.RANDOM_CHALLENGES_OPTIONS, 0)
    ;
    //</editor-fold>

    public final Inventory inv;

    public final ItemStack fillerItem;
    public final ItemStack lineFillerItem;

    public GuiOptions options;
    public final int spacing;

    private void fillInventory() {
        for (int currentSlot = 0; currentSlot < inv.getSize(); currentSlot++) {
            inv.setItem(currentSlot, fillerItem);
            if (currentSlot % 9 == 0) {
                inv.setItem(currentSlot, lineFillerItem);
            }
        }

        inv.setItem(inv.getSize() - 1, GuiItem.EXIT.itemStack);

    }

    public void updateInventory() {

    }

    public void setOptions(GuiOptions options) {
        this.options = options;
    }

    Gui(String title, int rows, GuiItem fillerItem, GuiItem lineFillerItem, GuiOptions options, int spacing) {
        this.inv = Bukkit.createInventory(null, rows * 9, title);
        this.fillerItem = fillerItem.itemStack;
        this.lineFillerItem = lineFillerItem.itemStack;
        this.options = options;
        this.spacing = spacing;

        fillInventory();
    }

    Gui(String title, int rows, ItemStack fillerItem, ItemStack lineFillerItem, GuiOptions options, int spacing) {
        this.inv = Bukkit.createInventory(null, rows * 9, title);
        this.fillerItem = fillerItem;
        this.lineFillerItem = lineFillerItem;
        this.options = options;
        this.spacing = spacing;

        fillInventory();
    }
}
