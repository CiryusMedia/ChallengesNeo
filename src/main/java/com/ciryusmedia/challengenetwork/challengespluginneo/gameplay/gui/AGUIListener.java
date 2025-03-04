package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.GeneralGuiItems;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class AGUIListener implements Listener, GeneralGuiItems {

    protected Inventory inv;

    protected ItemStack fillerItem;
    protected ItemStack lineFillerItem;

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        //Checks
        //Is the right inventory and the item isn't null
        if (!e.getInventory().equals(inv) || e.getCurrentItem() == null)
            return;

        //Player can't take Items out of Inventory
        e.setCancelled(true);

        if (item.equals(fillerItem) || item.equals(lineFillerItem))
            return;

        player.playSound(player, Sound.UI_BUTTON_CLICK, SoundCategory.MASTER, 100, 1);

        inventoryClickHandler(item, player);

        if (item.equals(exitItem))
            exitInventory(player);

        updateInventory();
    }

    public abstract void inventoryClickHandler(ItemStack item, Player player);

    public void exitInventory(Player player) {
        player.closeInventory();
    }

    public abstract void initInv();

    public abstract void updateInventory();

    /**
     * The super constructor for any gui listener
     * @param fillerItem An item that fills all empty spaces in a gui
     * @param lineFillerItem An item that fills all empty spaces in the first column of a gui, which fills the whole line because of a custom model
     */
    public AGUIListener(ItemStack fillerItem, ItemStack lineFillerItem) {
        this.fillerItem = fillerItem;
        this.lineFillerItem = lineFillerItem;

        initInv();
    }
}
