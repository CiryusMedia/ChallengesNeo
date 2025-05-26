package com.ciryusmedia.challengenetwork.challengespluginneo.core.util;

public interface ConfigPaths {

    //Challenges
    String CHALLENGE_PREFIX = ""; //Challenge config paths should be referenced through the path variable in the challenge enum

    //Goals
    String GOAL_PREFIX = ""; //Goal config paths should be referenced through the path variable in the goal enum

    //Coop settings
    String COOP_PREFIX = "";

    //Randomisation
    String RANDOM_PREFIX = "";
    String RANDOM_UNSAFE_ENCHANTMENTS = RANDOM_PREFIX + "UnsafeRandomEnchantments";
    String RANDOM_UNSAFE_ENCHANTMENT_BOUNDS = RANDOM_PREFIX + "UnsafeEnchantmentBounds";
    String RANDOM_UNSAFE_POTIONS = RANDOM_PREFIX + "UnsafeRandomPotions";
    String RANDOM_UNSAFE_POTION_BOUNDS = RANDOM_PREFIX + "UnsafeRandomPotionBounds";
    String RANDOM_POTION_DURATION = RANDOM_PREFIX + "RandomPotionDuration";

    //Timer
    String TIMER_PREFIX = "";
    String TIMER_COLOR_RUNNING = TIMER_PREFIX + "RunningColor";
    String TIMER_COLOR_PAUSED = TIMER_PREFIX + "PausedColor";
    String TIMER_TIME = TIMER_PREFIX + "Time";
    String TIMER_VISIBLE_RUNNING = TIMER_PREFIX + "Visible";
    String TIMER_VISIBLE_PAUSED = TIMER_PREFIX + "ShowPaused";

    //System
    String SYSTEM_PREFIX = "";
    String SYSTEM_PAUSED_BLOCK_BREAK = SYSTEM_PREFIX + "BlockBreakableWhilePaused";
    String SYSTEM_IS_RESET = SYSTEM_PREFIX + "isReset";
    String LOGGER_DEBUG_LEVEL = SYSTEM_PREFIX + "DebugLevel";

}
