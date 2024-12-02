package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class RandomMobsFullListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        int noOfDrops = event.getDrops().size();

        List<ItemStack> oldDrops = new ArrayList<>(event.getDrops());

        instance.log("Clearing drops", Debuglevel.LEVEL_3);
        event.getDrops().clear();
        instance.log("Replacing drops", Debuglevel.LEVEL_3);
        for (int i = 0; i < noOfDrops; i++) {
            ItemStack randomItem = rro.getRandomItem();
            randomItem.setAmount(oldDrops.get(i).getAmount());
            event.getDrops().add(randomItem);
        }
    }

    public RandomMobsFullListener(ChallengesPluginNeo instance, Plugin plugin, ChallengeTimer timer, Challenge challenge) {
        super(instance, plugin, timer, challenge);
    }
}
