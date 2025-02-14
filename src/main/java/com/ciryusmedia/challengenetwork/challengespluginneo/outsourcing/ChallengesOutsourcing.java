package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.blocks.RandomBlocksFull;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.blocks.RandomBlocksLoottable;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.entities.RandomMobsFull;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.entities.RandomMobsLoottable;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.synched.InventorySync;

import java.util.ArrayList;
import java.util.List;

public class ChallengesOutsourcing {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    public final List<Challenge> CHALLENGES = new ArrayList<>();

    public final List<String> TYPES = new ArrayList<>();

    //Random Challenges
    public final Challenge RANDOM_BLOCKS_LOOTTABLE = new RandomBlocksLoottable();
    public final Challenge RANDOM_BLOCKS_FULL = new RandomBlocksFull();
    public final Challenge RANDOM_MOBS_LOOTTABLE = new RandomMobsLoottable();
    public final Challenge RANDOM_MOBS_FULL = new RandomMobsFull();

    public final Challenge INVENTORY_SYNC = new InventorySync();

    public void initChallenges() {
        //Challenges
        CHALLENGES.add(RANDOM_BLOCKS_LOOTTABLE);
        CHALLENGES.add(RANDOM_BLOCKS_FULL);
        CHALLENGES.add(RANDOM_MOBS_LOOTTABLE);
        CHALLENGES.add(RANDOM_MOBS_FULL);

        CHALLENGES.add(INVENTORY_SYNC);

        CHALLENGES.forEach(challenge -> {challenge.setEnabled(plugin.getConfig().getBoolean(challenge.getName()));});

        //Subtypes
        TYPES.add("random");
        TYPES.add("sync");
    }

    public Challenge getChallengeFromName(String name) {
        if (CHALLENGES.stream().anyMatch(c -> c.getName().equalsIgnoreCase(name))) {
            return CHALLENGES.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().get();
        } else {
            return null;
        }
    }

    public List<Challenge> getChallengesFromSubtype(String subtype) {
        List<Challenge> challenges = new ArrayList<>();

        CHALLENGES.forEach(c -> {
            if (c.getSubType().equalsIgnoreCase(subtype)) challenges.add(c);
        });

        return challenges;
    }

    public List<Challenge> getChallengesFromType(String subtype) {
        List<Challenge> challenges = new ArrayList<>();

        CHALLENGES.forEach(c -> {
            if (c.getType().equalsIgnoreCase(subtype)) challenges.add(c);
        });

        return challenges;
    }

    public boolean isValidType(String subtype) {
        return TYPES.contains(subtype);
    }

    public ChallengesOutsourcing() {
        initChallenges();
    }

    public List<Challenge> getCHALLENGES() {
        return CHALLENGES;
    }

}
