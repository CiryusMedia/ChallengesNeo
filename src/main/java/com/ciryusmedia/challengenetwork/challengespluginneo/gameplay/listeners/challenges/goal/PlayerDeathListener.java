package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@SuppressWarnings({"deprecation"})
public class PlayerDeathListener extends AGoal implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
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

}
