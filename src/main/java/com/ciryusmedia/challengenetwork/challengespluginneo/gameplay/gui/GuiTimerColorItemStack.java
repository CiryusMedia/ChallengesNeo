package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class GuiTimerColorItemStack extends GuiItemStack {

    private static final FileConfiguration config = ChallengesPluginNeo.getChallengePlugin().getConfig();

    public final String key;

    public GuiTimerColorItemStack clone(boolean running) {
        GuiTimerColorItemStack itemStack = new GuiTimerColorItemStack(this.getType(), this.getDisplayName(), this.getCustomModelData(), this.key);
        itemStack.setEnchantmentGlint(isActiveColor(running));
        return itemStack;
    }

    private boolean isActiveColor(boolean running) {
        if (running) {
            return config.getString(TIMER_COLOR_RUNNING).equalsIgnoreCase(this.key);
        } else {
            return config.getString(TIMER_COLOR_PAUSED).equalsIgnoreCase(this.key);
        }
    }

    public GuiTimerColorItemStack(Material material, String displayName, List<String> customModelData, String key) {
        super(material, displayName, customModelData);
        this.key = key;
    }
}
