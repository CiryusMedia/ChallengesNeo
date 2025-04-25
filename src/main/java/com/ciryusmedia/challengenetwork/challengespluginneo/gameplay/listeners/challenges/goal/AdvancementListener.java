package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.HashSet;
import java.util.Set;

public class AdvancementListener extends AGoal implements Listener {

    private final BossBar bossBar;

    private final Set<String> completedAdvancements = new HashSet<>();
    private final Set<String> allAdvancements = new HashSet<>();

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Advancement advancement = event.getAdvancement();

        if (completedAdvancements.contains(advancement.getKey().toString())) {
            return;
        }

        Bukkit.getOnlinePlayers().forEach(p -> {
            for (String criteria : event.getAdvancement().getCriteria()) {
                p.getAdvancementProgress(advancement).awardCriteria(criteria);
            }
        });

        if (player.getAdvancementProgress(advancement).isDone()) {
            completedAdvancements.add(advancement.getKey().toString());
            bossBar.setTitle("Advancements: " + completedAdvancements.size());
        }

        if (timer.isRunning() && completedAdvancements.containsAll(allAdvancements)) {
            beatRun();
        }
    }

    public AdvancementListener() {
        bossBar = Bukkit.createBossBar("Advancements: 0", BarColor.GREEN, BarStyle.SOLID, BarFlag.CREATE_FOG);
        Bukkit.advancementIterator().forEachRemaining(advancement -> allAdvancements.add(advancement.getKey().toString()));
    }

}
