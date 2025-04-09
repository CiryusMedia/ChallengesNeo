package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.ArrayList;
import java.util.List;

public class AdvancementListener implements Listener {

    private final BossBar bossBar;

    private final List<Component> completedAdvancements = new ArrayList();

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        if (completedAdvancements.contains(event.getAdvancement().getDisplay().title())) {
            return;
        }
        completedAdvancements.add(event.getAdvancement().getDisplay().title());
        Bukkit.getOnlinePlayers().forEach(player -> {
            for (String criteria : event.getAdvancement().getCriteria()) {
                player.getAdvancementProgress(event.getAdvancement()).awardCriteria(criteria);
            }
        });
        bossBar.setTitle("Advancements: " + completedAdvancements.size());
    }

    public AdvancementListener() {
        bossBar = Bukkit.createBossBar("Advancements: 0", BarColor.GREEN, BarStyle.SOLID, BarFlag.CREATE_FOG);
    }

}
