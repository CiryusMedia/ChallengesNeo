package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
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

    public static final List<ChallengeOld> CHALLENGE_OLDS = new ArrayList<>();

    public static final List<String> TYPES = new ArrayList<>();

    //Random Challenges
    public static final ChallengeOld RANDOM_BLOCKS_LOOTTABLE = new RandomBlocksLoottable();
    public static final ChallengeOld RANDOM_BLOCKS_FULL = new RandomBlocksFull();
    public static final ChallengeOld RANDOM_MOBS_LOOTTABLE = new RandomMobsLoottable();
    public static final ChallengeOld RANDOM_MOBS_FULL = new RandomMobsFull();

    public static final ChallengeOld INVENTORY_SYNC = new InventorySync();

    public static void initChallenges() {
        plugin.log("Initiating cho", Debuglevel.LEVEL_2);

        //Challenges
        CHALLENGE_OLDS.add(RANDOM_BLOCKS_LOOTTABLE);
        CHALLENGE_OLDS.add(RANDOM_BLOCKS_FULL);
        CHALLENGE_OLDS.add(RANDOM_MOBS_LOOTTABLE);
        CHALLENGE_OLDS.add(RANDOM_MOBS_FULL);

        CHALLENGE_OLDS.add(INVENTORY_SYNC);

        CHALLENGE_OLDS.forEach(challenge -> {challenge.setEnabled(plugin.getConfig().getBoolean(challenge.getName()));});

        //Subtypes
        TYPES.add("random");
        TYPES.add("sync");

        isInitialized = true;
    }

    public static ChallengeOld getChallengeFromName(String name) {
        if (CHALLENGE_OLDS.stream().anyMatch(c -> c.getName().equalsIgnoreCase(name))) {
            return CHALLENGE_OLDS.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().get();
        } else {
            return null;
        }
    }

    public static List<ChallengeOld> getChallengesFromSubtype(String subtype) {
        List<ChallengeOld> challengeOlds = new ArrayList<>();

        CHALLENGE_OLDS.forEach(c -> {
            if (c.getSubType().equalsIgnoreCase(subtype)) challengeOlds.add(c);
        });

        return challengeOlds;
    }

    public static List<ChallengeOld> getChallengesFromType(String subtype) {
        List<ChallengeOld> challengeOlds = new ArrayList<>();

        CHALLENGE_OLDS.forEach(c -> {
            if (c.getType().equalsIgnoreCase(subtype)) challengeOlds.add(c);
        });

        return challengeOlds;
    }

    public static boolean isValidType(String subtype) {
        return TYPES.contains(subtype);
    }

    public static List<ChallengeOld> getCHALLENGES() {
        return CHALLENGE_OLDS;
    }

    public static void checkInitialized() throws DataNotInitializedException {
        if (!isInitialized) {
            throw new DataNotInitializedException("ColorOutsourcing from ChallengesPluginNeo is not initialized!");
        }
    }

}
