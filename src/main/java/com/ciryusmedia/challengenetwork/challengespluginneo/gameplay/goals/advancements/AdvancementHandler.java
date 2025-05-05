package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.advancements;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;

import java.util.HashSet;
import java.util.Set;

public class AdvancementHandler {
    //TODO handle advancement tracking -> cut from AdvancementListener
    private final Set<NamespacedKey> completedAdvancements = new HashSet<>();
    private final Set<NamespacedKey> allAdvancements = new HashSet<>();

    public void refreshAdvancements() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            allAdvancements.forEach(a -> {
                if (p.getAdvancementProgress(Bukkit.getAdvancement(a)).isDone()) {
                    completedAdvancements.add(a);
                }
            });
        });
    }

    public boolean advancementCompleted(Advancement advancement) {
        return completedAdvancements.contains(advancement.getKey());
    }

    public boolean allAdvancementsCompleted() {
        return completedAdvancements.containsAll(allAdvancements);
    }

    public void completeAdvancement(Advancement advancement) {
        completedAdvancements.add(advancement.getKey());
    }

    public int countCompletedAdvancements() {
        return completedAdvancements.size();
    }

    public Set<NamespacedKey> getCompletedAdvancements() {
        return completedAdvancements;
    }

    public Set<NamespacedKey> getAllAdvancements() {
        return allAdvancements;
    }
}
