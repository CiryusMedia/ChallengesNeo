package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"DataFlowIssue", "deprecation"})
public abstract class AGoal {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    ChallengeTimer timer = plugin.getTimer();

    public void beatRun() {
        endRun(true, null);
    }

    public void failRun(String reason) {
        //For now only dying fails a run. Might be changed with future updates
        endRun(false, reason);
    }

    public void endRun(boolean success, String failReason) {
        if (success) {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was successfully beaten with a time of "
                    + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD + "!");
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was failed after " + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD
                    + " because '" + ChatColor.RED + failReason + ChatColor.GOLD + "'!");
        }
        if (!Challenge.getActiveChallengesList().isEmpty()) {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The following challenges were used: \n" + Challenge.getActiveChallengesString());
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "No Challenges were used");
        }
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "GGWP! The seed was: " + ChatColor.DARK_AQUA + Bukkit.getServer().getWorld("world").getSeed());
        timer.setRunning(false);
    }
}
