package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@SuppressWarnings({"deprecation"})
public class PlayerDeathListener extends AGoal implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!goal.isEnabled()) return;
        if (timer.isRunning()) {
            failRun(event.getDeathMessage());
            event.setDeathMessage("");

            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setGameMode(GameMode.SPECTATOR);
            });

            event.setCancelled(true);
            return;
        }
    }

    public PlayerDeathListener() {
        goal = Goal.PLAYER_DEATH;
    }

}
