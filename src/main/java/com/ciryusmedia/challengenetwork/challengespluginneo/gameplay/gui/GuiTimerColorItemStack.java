package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.Material;

import java.util.List;

public class GuiTimerColorItemStack extends GuiItemStack {

    private static final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    public final String key;

    public GuiTimerColorItemStack clone(boolean running) {
        GuiTimerColorItemStack itemStack = new GuiTimerColorItemStack(this.getType(), this.getDisplayName(), this.getCustomModelData(), this.key);
        itemStack.setEnchantmentGlint(isActiveColor(running));
        return itemStack;
    }

    private boolean isActiveColor(boolean running) {
        if (running) {
            return plugin.getConfig().getString(TIMER_COLOR_RUNNING).equalsIgnoreCase(this.key);
        } else {
            return plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase(this.key);
        }
    }

    public GuiTimerColorItemStack(Material material, String displayName, List<String> customModelData, String key) {
        super(material, displayName, customModelData);
        this.key = key;
    }
}
