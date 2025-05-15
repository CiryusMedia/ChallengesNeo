package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GuiTimerColorItemStack extends GuiItemStack {

    private static final FileConfiguration config = ChallengesPluginNeo.getChallengePlugin().getConfig();

    private boolean running; //TODO Make this actually useful
    public final String key;

    public GuiTimerColorItemStack clone(boolean running) {
        GuiTimerColorItemStack itemStack = new GuiTimerColorItemStack(this.getType(), this.getDisplayName(), this.getCustomModelData(), this.key, running);
        itemStack.setEnchantmentGlint(isActiveColor(this.running));
        return itemStack;
    }

    private boolean isActiveColor(boolean running) {
        if (running) {
            return config.getString(TIMER_COLOR_RUNNING).equalsIgnoreCase(this.key);
        } else {
            return config.getString(TIMER_COLOR_PAUSED).equalsIgnoreCase(this.key);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return this.running;
    }

    public GuiTimerColorItemStack(Material material, String displayName, List<String> customModelData, String key) {
        super(material, displayName, customModelData);
        this.key = key;
    }

    public GuiTimerColorItemStack(Material material, String displayName, List<String> customModelData, String key, boolean running) {
        super(material, displayName, customModelData);
        this.key = key;
        this.running = running;
    }
}
