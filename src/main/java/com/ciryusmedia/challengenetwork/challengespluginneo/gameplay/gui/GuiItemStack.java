package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuiItemStack extends ItemStack {

    public String getDisplayName() {
        return getItemMeta().getDisplayName();
    }

    public GuiItemStack(Material material, String displayName) {
        this(material, displayName, (List<String>) null, null);
    }

    public GuiItemStack(Material material, String displayName, String customModelData) {
        this(material, displayName, customModelData, null);
    }

    public GuiItemStack(Material material, String displayName, String customModelData, List<String> lore) {
        this(material, displayName, List.of(customModelData), lore);
    }

    public GuiItemStack(Material material, String displayName, @Nullable List<String> customModelData) {
        this(material, displayName, customModelData, null);
    }

    public GuiItemStack(Material material, String displayName, @Nullable List<String> customModelData, @Nullable List<String> lore) {
        super(material);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(displayName);
        if (customModelData != null) {
            CustomModelDataComponent modelData = meta.getCustomModelDataComponent();
            modelData.setStrings(customModelData);
            meta.setCustomModelDataComponent(modelData);
        }
        if (lore != null) meta.setLore(lore);
        setItemMeta(meta);
    }
}
