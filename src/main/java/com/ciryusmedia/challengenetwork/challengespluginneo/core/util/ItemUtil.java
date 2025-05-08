package com.ciryusmedia.challengenetwork.challengespluginneo.core.util;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public interface ItemUtil {

    default void updateItem(ItemStack item, List<String> description, boolean enabled, String displayName) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(description);

        itemMeta.setEnchantmentGlintOverride(enabled);

        itemMeta.setDisplayName(enabled ? ChatColor.GREEN + displayName : ChatColor.RED + displayName);
        lore.add(""); //Empty spacer line
        lore.add(displayName + " is currently " + (enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

    }

}
