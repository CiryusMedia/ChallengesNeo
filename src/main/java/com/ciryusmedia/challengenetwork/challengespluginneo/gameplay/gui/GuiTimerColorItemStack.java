package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui;

import org.bukkit.Material;

import java.util.List;

public class GuiTimerColorItemStack extends GuiItemStack {

    public final boolean running; //TODO Make this actually useful
    public final String key;

    public GuiTimerColorItemStack(Material material, String displayName, List<String> customModelData, String key, boolean running) {
        super(material, displayName, customModelData);
        this.key = key;
        this.running = running;
    }
}
