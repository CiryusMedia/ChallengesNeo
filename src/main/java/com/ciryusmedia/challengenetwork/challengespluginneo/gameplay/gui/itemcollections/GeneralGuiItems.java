package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface GeneralGuiItems {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    List<String> CMD_INVIS = List.of("invisible"); //Making these a List allows for appending multiple model data
    List<String> CMD_FILLER = CMD_INVIS;
    List<String> CMD_LINE_FILLER = List.of("line_filler");
    List<String> CMD_COLOR_WOOL = List.of("color_wool");
    List<String> CMD_EXIT = List.of("gui_exit");


    GuiItemStack defaultFillerItem = new GuiItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack defaultLineFillerItem = new GuiItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);

    GuiItemStack exitItem = new GuiItemStack(Material.BARRIER, ChatColor.RED + "Exit", CMD_EXIT);

    default void emptyInventoryItemFiller(Inventory inventory, ItemStack fillerItem, ItemStack lineFillerItem) {
        for (int currentSlot = 0; currentSlot < inventory.getSize(); currentSlot++) {
            inventory.setItem(currentSlot, fillerItem);
            if (currentSlot % 9 == 0) {
                inventory.setItem(currentSlot, lineFillerItem);
            }
        }
    }

    static ItemMeta setEnchantmentGlint(ItemStack itemStack, boolean value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setEnchantmentGlintOverride(value);
        return itemMeta;
    }
}
