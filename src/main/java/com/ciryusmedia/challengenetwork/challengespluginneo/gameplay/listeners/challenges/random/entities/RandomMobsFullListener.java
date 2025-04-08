package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.RandomisationUtils;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomMobsFullListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        RandomisationUtils.checkInitialized();

        int noOfDrops = event.getDrops().size();

        List<ItemStack> oldDrops = new ArrayList<>(event.getDrops());

        LOGGER.debug("Clearing drops", DebugLevel.LEVEL_3);
        event.getDrops().clear();
        LOGGER.debug("Replacing drops", DebugLevel.LEVEL_3);
        for (int i = 0; i < noOfDrops; i++) {
            ItemStack randomItem = RandomisationUtils.getRandomItem();
            randomItem.setAmount(oldDrops.get(i).getAmount());
            event.getDrops().add(randomItem);
        }
    }

    public RandomMobsFullListener(Challenge challenge) {
        super(challenge);
    }
}
