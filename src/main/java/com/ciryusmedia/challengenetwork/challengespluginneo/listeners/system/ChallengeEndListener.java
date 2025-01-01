package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.system;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DataFlowIssue")
public class ChallengeEndListener implements Listener {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();
    Plugin plugin = ChallengesPluginNeo.getPlugin(ChallengesPluginNeo.class);

    @EventHandler
    public void onDragonDeath(EntityDeathEvent event) {

        if (!(event.getEntity() instanceof EnderDragon))
            return;

        ChallengeTimer timerOLD = instance.getTimer();

        StringBuilder usedChallenges = new StringBuilder();
        List<String> usedChallengesList = new ArrayList<>();
        getActiveChallenges(usedChallenges, usedChallengesList);

        if (timerOLD.isRunning()) {
            timerOLD.setRunning(false);
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was successfully beaten with a time of "
                    + ChatColor.AQUA + timerOLD.getStringFromTime(timerOLD.getTime()) + ChatColor.GOLD + "!");
            if (!usedChallenges.isEmpty())
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The following challenges were used: \n" + usedChallenges);
            else
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "No Challenges were used");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "GGWP! The seed was: " + ChatColor.DARK_AQUA + Bukkit.getServer().getWorld("world").getSeed());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        ChallengeTimer timer = instance.getTimer();
        String deathMessage = event.getDeathMessage();

        StringBuilder usedChallenges = new StringBuilder();
        List<String> usedChallengesList = new ArrayList<>();
        getActiveChallenges(usedChallenges, usedChallengesList);

        if (!timer.isRunning()) {
            return;
        }

        timer.setRunning(false);
        event.setDeathMessage("");
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was failed after " + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD
                + " because '" + ChatColor.RED + deathMessage + ChatColor.GOLD + "'!");
        if (!usedChallenges.isEmpty())
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The following challenges were used: \n" + usedChallenges);
        else
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "No Challenges were used");
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "GGWP! The seed was: " + ChatColor.DARK_AQUA + Bukkit.getServer().getWorld("world").getSeed());


        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setGameMode(GameMode.SPECTATOR);
        });

        event.setCancelled(true);
    }

    private void getActiveChallenges(StringBuilder usedChallenges, List<String> usedChallengesList) {
        instance.getCho().CHALLENGES.forEach(challenge -> {
            if (challenge.isEnabled()){
                usedChallengesList.add(challenge.getDisplayName());
                instance.log("Active challenge " + challenge.getDisplayName(), Debuglevel.LEVEL_3);
            }
            instance.log("Active challenges: " + usedChallengesList, Debuglevel.LEVEL_3);
        });

        for (int i = 0; i < usedChallengesList.toArray().length; i++) {
            usedChallenges.append(usedChallengesList.toArray()[i]);
            if (usedChallengesList.size() > i + 1) {
                usedChallenges.append(", ");
            }
        }
    }
}
