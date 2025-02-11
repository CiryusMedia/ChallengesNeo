package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.blocks.RandomBlocksFull;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.blocks.RandomBlocksLoottable;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.entities.RandomMobsFull;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.random.entities.RandomMobsLoottable;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.synched.InventorySync;
import com.ciryusmedia.challengenetwork.challengespluginneo.exceptions.DataNotInitializedException;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;

import java.util.ArrayList;
import java.util.List;

public class ChallengesOutsourcing {

    static ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    private static boolean isInitialized = false;

    public static final List<Challenge> CHALLENGES = new ArrayList<>();

    public static final List<String> TYPES = new ArrayList<>();

    //Random Challenges
    public static final Challenge RANDOM_BLOCKS_LOOTTABLE = new RandomBlocksLoottable();
    public static final Challenge RANDOM_BLOCKS_FULL = new RandomBlocksFull();
    public static final Challenge RANDOM_MOBS_LOOTTABLE = new RandomMobsLoottable();
    public static final Challenge RANDOM_MOBS_FULL = new RandomMobsFull();

    public static final Challenge INVENTORY_SYNC = new InventorySync();

    public static void initChallenges() {
        plugin.log("Initiating cho", Debuglevel.LEVEL_2);

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

        isInitialized = true;
    }

    public static Challenge getChallengeFromName(String name) {
        if (CHALLENGES.stream().anyMatch(c -> c.getName().equalsIgnoreCase(name))) {
            return CHALLENGES.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().get();
        } else {
            return null;
        }
    }

    public static List<Challenge> getChallengesFromSubtype(String subtype) {
        List<Challenge> challenges = new ArrayList<>();

        CHALLENGES.forEach(c -> {
            if (c.getSubType().equalsIgnoreCase(subtype)) challenges.add(c);
        });

        return challenges;
    }

    public static List<Challenge> getChallengesFromType(String subtype) {
        List<Challenge> challenges = new ArrayList<>();

        CHALLENGES.forEach(c -> {
            if (c.getType().equalsIgnoreCase(subtype)) challenges.add(c);
        });

        return challenges;
    }

    public static boolean isValidType(String subtype) {
        return TYPES.contains(subtype);
    }

    public static List<Challenge> getCHALLENGES() {
        return CHALLENGES;
    }

    public static void checkInitialized() throws DataNotInitializedException {
        if (!isInitialized) {
            throw new DataNotInitializedException("ColorOutsourcing from ChallengesPluginNeo is not initialized!");
        }
    }

}
