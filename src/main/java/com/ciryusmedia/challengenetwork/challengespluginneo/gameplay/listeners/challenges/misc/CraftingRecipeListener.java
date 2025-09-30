package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.misc;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.AChallengeListener;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class CraftingRecipeListener extends AChallengeListener implements Listener { //Not really a Listener, but it's how we sort things around here

    public static final CraftingRecipeListener INST = new CraftingRecipeListener();

    public void run() {
        new BukkitRunnable() {
            public void run() {
                Bukkit.getWorld("world").setGameRule(GameRule.DO_LIMITED_CRAFTING, challenge.enabled);
            }
        }.runTaskTimer(ChallengesPluginNeo.getChallengePlugin(), 20, 10);
    }

    public CraftingRecipeListener() {
        super(Challenge.CRAFTING_RECIPE);
        run();
    }
}
