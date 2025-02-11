package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
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

        plugin.log("Clearing drops", Debuglevel.LEVEL_3);
        event.getDrops().clear();
        plugin.log("Replacing drops", Debuglevel.LEVEL_3);
        for (int i = 0; i < noOfDrops; i++) {
            ItemStack randomItem = ChallengeRandomisation.getRandomItem();
            randomItem.setAmount(oldDrops.get(i).getAmount());
            event.getDrops().add(randomItem);
        }
    }

    public RandomMobsFullListener(ChallengeOld challengeOld) {
        super(challengeOld);
    }
}
