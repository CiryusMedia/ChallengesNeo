package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

@SuppressWarnings({"DataFlowIssue","deprecation"})
public class AdvancementListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

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

        if (!player.getAdvancementProgress(advancement).isDone()) {
            return;
        }

        completedAdvancements.add(advancement.getKey().toString());

        if (completedAdvancements.containsAll(allAdvancements)) {
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

        bossBar.setTitle("Advancements: " + completedAdvancements.size());
    }

    public AdvancementListener() {
        bossBar = Bukkit.createBossBar("Advancements: 0", BarColor.GREEN, BarStyle.SOLID, BarFlag.CREATE_FOG);
        Bukkit.advancementIterator().forEachRemaining(advancement -> allAdvancements.add(advancement.getKey().toString()));
    }

}
