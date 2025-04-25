package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"DataFlowIssue","deprecation"})
public class EnderdragonDeathListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    @EventHandler
    public void onDragonDeath(EntityDeathEvent event) {

        if (!(event.getEntity() instanceof EnderDragon))
            return;

        ChallengeTimer timer = plugin.getTimer();

        if (timer.isRunning()) {
            timer.setRunning(false);
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was successfully beaten with a time of "
                    + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD + "!");
            if (!Challenge.getActiveChallengesList().isEmpty()) {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The following challenges were used: \n" + Challenge.getActiveChallengesString());
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "No Challenges were used");
            }
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "GGWP! The seed was: " + ChatColor.DARK_AQUA + Bukkit.getServer().getWorld("world").getSeed());
        }
    }
}
