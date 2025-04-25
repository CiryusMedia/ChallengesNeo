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

@SuppressWarnings({"DataFlowIssue","deprecation"})
public class PlayerDeathListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        ChallengeTimer timer = plugin.getTimer();
        String deathMessage = event.getDeathMessage();

        if (!timer.isRunning()) {
            return;
        }

        timer.setRunning(false);
        event.setDeathMessage("");
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was failed after " + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD
                + " because '" + ChatColor.RED + deathMessage + ChatColor.GOLD + "'!");
        if (!Challenge.getActiveChallengesList().isEmpty())
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The following challenges were used: \n" + Challenge.getActiveChallengesString());
        else
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "No Challenges were used");
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "GGWP! The seed was: " + ChatColor.DARK_AQUA + Bukkit.getServer().getWorld("world").getSeed());


        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setGameMode(GameMode.SPECTATOR);
        });

        event.setCancelled(true);
    }

}
