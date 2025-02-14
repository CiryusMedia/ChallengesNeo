package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomMobsFullListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        ChallengeRandomisation.checkInitialized();

        int noOfDrops = event.getDrops().size();

        List<ItemStack> oldDrops = new ArrayList<>(event.getDrops());

        DEBUGGER.log("Clearing drops", DebugLevel.LEVEL_3);
        event.getDrops().clear();
        DEBUGGER.log("Replacing drops", DebugLevel.LEVEL_3);
        for (int i = 0; i < noOfDrops; i++) {
            ItemStack randomItem = ChallengeRandomisation.getRandomItem();
            randomItem.setAmount(oldDrops.get(i).getAmount());
            event.getDrops().add(randomItem);
        }
    }

    public RandomMobsFullListener(Challenge challenge) {
        super(challenge);
    }
}
