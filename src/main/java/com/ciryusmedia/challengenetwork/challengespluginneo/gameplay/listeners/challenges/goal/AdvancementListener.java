package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.advancements.AdvancementHandler;

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

public class AdvancementListener extends AGoal implements Listener {

    public static final AdvancementListener INST = new AdvancementListener();

    private final BossBar bossBar;
    private final AdvancementHandler advancementHandler;

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Advancement advancement = event.getAdvancement();

        if (advancementHandler.advancementCompleted(advancement)) {
            return;
        }

        Bukkit.getOnlinePlayers().forEach(p -> {
            for (String criteria : event.getAdvancement().getCriteria()) {
                p.getAdvancementProgress(advancement).awardCriteria(criteria);
            }
        });

        if (player.getAdvancementProgress(advancement).isDone()) {
            advancementHandler.completeAdvancement(advancement);
            bossBar.setTitle("Advancements: " + advancementHandler.countCompletedAdvancements());
        }

        if (goal.isEnabled() && timer.isRunning() && advancementHandler.allAdvancementsCompleted()) {
            beatRun(player);
        }
    }

    public AdvancementListener() {
        goal = Goal.GET_ALL_ADVANCEMENTS;
        bossBar = Bukkit.createBossBar("Advancements: 0", BarColor.GREEN, BarStyle.SOLID, BarFlag.CREATE_FOG);
        advancementHandler = plugin.getAdvancementHandler();
    }

}
