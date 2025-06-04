package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.comp.Comp;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"DataFlowIssue", "deprecation"})
public abstract class AGoal {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    ChallengeTimer timer = plugin.getTimer();

    protected Goal goal;

    public void beatRun(@Nullable Player player) {
        endRun(true, null, player);
    }

    public void failRun(String reason, @Nullable Player player) {
        //For now only dying fails a run. Might be changed with future updates
        endRun(false, reason, player);
    }

    public void endRun(boolean success, @Nullable String failReason, @Nullable Player player) {
        if (success) {
            if (Comp.isCoop() || player == null) {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was successfully beaten with a time of "
                        + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD + "!");
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was successfully beaten by " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD
                        + " with a time of " + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD + "!");
            }
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The challenge was failed after " + ChatColor.AQUA + timer.getStringFromTime(timer.getTime()) + ChatColor.GOLD
                    + " because \"" + ChatColor.RED + failReason + ChatColor.GOLD + "\"!");
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
