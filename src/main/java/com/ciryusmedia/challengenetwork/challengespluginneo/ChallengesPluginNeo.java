package com.ciryusmedia.challengenetwork.challengespluginneo;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.loader.FileLoader;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.loader.WorldLoader;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ConfigPaths;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.RandomisationUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.*;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.ChallengeComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.DebugComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.GoalComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.TimerComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.advancements.AdvancementHandler;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.ChallengeGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.random.RandomChallengesGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.goals.GoalsGui;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.TimerColorInvGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.TimerGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color.TimerPausedColorGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color.TimerRunningColorGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal.AdvancementListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal.EnderdragonDeathListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal.PlayerDeathListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks.RandomBlocksFullListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks.RandomBlocksLoottableListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities.RandomMobsFullListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities.RandomMobsLoottableListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.synched.InventorySyncListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system.BlockBreakListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system.PlayerJoinLeaveListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.scoreboards.HealthScoreboard;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.io.IOException;

@SuppressWarnings({"DataFlowIssue", "deprecation"})
public final class ChallengesPluginNeo extends JavaPlugin implements PluginMessageListener, WorldLoader, ConfigPaths {

    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    private static ChallengesPluginNeo instance;

    private ChallengeTimer timer;
    private FileLoader fileLoader;

    private Scoreboard scoreboard;

    private AdvancementHandler advancementHandler;

    //Inventories
    public static TimerGUI timerGUI;
    public static TimerColorInvGUI timerColorGui;
    public static TimerRunningColorGUI timerRunningColorGUI;
    public static TimerPausedColorGUI timerPausedColorGUI;

    public static ChallengeGUI challengeGUI;
    public static RandomChallengesGUI randomChallengesGUI;

    public static GoalsGui goalsGUI;

    //Scoreboard Objectives
    HealthScoreboard healthScoreboard;

    //Startup and shutdown
    @Override
    public void onLoad() {
        LOGGER.log("Loading Ciryus Challenge Plugin version " + getDescription().getVersion());

        LOGGER.setMessagePrefix(ChatColor.AQUA + "Challenge" + ChatColor.DARK_GRAY + "> ");
        instance = this;

        //Config stuff
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        if (!getConfig().contains(LOGGER_DEBUG_LEVEL) || getConfig().getInt(LOGGER_DEBUG_LEVEL) < 0) {
            getConfig().set(LOGGER_DEBUG_LEVEL, DebugLevel.LEVEL_1.level);
            LOGGER.debug("Debuglevel not found, setting to \"LEVEL_1\"", DebugLevel.LEVEL_1);
        }
        LOGGER.setDebugLevel(getConfig().getInt(LOGGER_DEBUG_LEVEL));
        LOGGER.debug("Debuglevel: " + LOGGER.getDebugLevel(), DebugLevel.LEVEL_1);
        saveConfig();
        reloadConfig();

        //Reset world reset
        if (!getConfig().contains(SYSTEM_IS_RESET)) {
            getConfig().set(SYSTEM_IS_RESET, false);
            saveConfig();
        }
        if (getConfig().getBoolean(SYSTEM_IS_RESET)) {
            resetWorld();
            getConfig().set(TIMER_TIME, 0);
            getConfig().set(SYSTEM_IS_RESET, false);
            saveConfig();
        }

        try {
            String dataFolderPath = getDataFolder().getCanonicalPath();
            LOGGER.debug(dataFolderPath, DebugLevel.LEVEL_5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileLoader = new FileLoader(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Messages
        LOGGER.log("Enabling Ciryus Challenge Plugin " + getDescription().getVersion());

        //Bungeecord messenger channels
        LOGGER.debug("Registering plugin channels", DebugLevel.LEVEL_1);

        LOGGER.debug("Incoming custom:network", DebugLevel.LEVEL_2);
        getServer().getMessenger().registerIncomingPluginChannel(this, "custom:network", this);

        //Challenge
        LOGGER.debug("Challenge system", DebugLevel.LEVEL_1);
        LOGGER.debug("Timer", DebugLevel.LEVEL_2);
        timer = new ChallengeTimer(false, 0);
        timer.setTime(getConfig().getInt(TIMER_TIME));

        RandomisationUtils.initRandomisation();

        //Initiate and enable
        LOGGER.debug("Initiating objects", DebugLevel.LEVEL_1);
        initInventories();
        advancementHandler = new AdvancementHandler();

        LOGGER.debug("Enabling plugin logic", DebugLevel.LEVEL_1);
        enableEvents();
        initScoreboard();
        enableCommands();
        enableTabcomplete();

        //Config
        LOGGER.debug("Loading default config", DebugLevel.LEVEL_2);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Activating tick
        LOGGER.debug("Starting tick", DebugLevel.LEVEL_1);
        tick();

        //Finished loading
        LOGGER.log(ChatColor.RESET + Texts.STARTUP_LOGO);
        LOGGER.log("Challenge Plugin Loaded and Enabled");

        //Test restarts
//        getRandomBlocksLoottableConfigFile().delete();
//        getRandomMobsLoottableConfigFile().delete();
//        getConfig().set("isReset", true);
//        saveConfig();
//        Bukkit.shutdown();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //Config file
        getConfig().set(TIMER_TIME, timer.getTime());
        saveConfig();

        //Bungeecord messenger channels
        getServer().getMessenger().unregisterIncomingPluginChannel(this);
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (s.equals("custom:network")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subChannel = in.readUTF();

            //EndAll command from Bungeecord
            if (subChannel.equals("EndAll")) {
                getServer().shutdown();
            }
        }
    }

    private void tick() {
        new BukkitRunnable() {
            @Override
            public void run() {
                reloadConfig();
                LOGGER.setDebugLevel(getConfig().getInt(LOGGER_DEBUG_LEVEL));
                updateInventories();
                Goal.updateAllEnabled();
            }
        }.runTaskTimer(ChallengesPluginNeo.getChallengePlugin(), 20, 20);
    }

    //Inits and enablers
    private void enableCommands() {
        LOGGER.debug("Commands", DebugLevel.LEVEL_2);
        getCommand("debug").setExecutor(new DebugCommand());
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("goals").setExecutor(new GoalCommand());
    }

    private void enableTabcomplete() {
        LOGGER.debug("Tabcomplete", DebugLevel.LEVEL_2);
        getCommand("timer").setTabCompleter(new TimerComplete());
        getCommand("challenge").setTabCompleter(new ChallengeComplete());
        getCommand("debug").setTabCompleter(new DebugComplete());
        getCommand("goal").setTabCompleter(new GoalComplete());
    }

    private void enableEvents() {
        LOGGER.debug("Events", DebugLevel.LEVEL_2);
        //System
        LOGGER.debug("System listeners", DebugLevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        //GUI
        LOGGER.debug("GUI listeners", DebugLevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(timerGUI, this);
        getServer().getPluginManager().registerEvents(timerColorGui, this);
        getServer().getPluginManager().registerEvents(timerRunningColorGUI, this);
        getServer().getPluginManager().registerEvents(timerPausedColorGUI, this);

        getServer().getPluginManager().registerEvents(challengeGUI, this);
        getServer().getPluginManager().registerEvents(randomChallengesGUI, this);

        getServer().getPluginManager().registerEvents(goalsGUI, this);

        //Challenges
        LOGGER.debug("Challenge listeners", DebugLevel.LEVEL_2);
        //Goals
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new EnderdragonDeathListener(), this);
        getServer().getPluginManager().registerEvents(new AdvancementListener(), this);

        //Random Challenges
        getServer().getPluginManager().registerEvents(new RandomBlocksLoottableListener(Challenge.RANDOM_BLOCKS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomBlocksFullListener(Challenge.RANDOM_BLOCKS_FULL), this);
        getServer().getPluginManager().registerEvents(new RandomMobsLoottableListener(Challenge.RANDOM_MOBS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomMobsFullListener(Challenge.RANDOM_MOBS_FULL), this);

        //Sync Challenges
        getServer().getPluginManager().registerEvents(new InventorySyncListener(Challenge.INVENTORY_SYNC), this);
    }

    private void initInventories() {
        LOGGER.debug("Inventories", DebugLevel.LEVEL_2);
        timerGUI = new TimerGUI();
        timerColorGui = new TimerColorInvGUI();
        timerRunningColorGUI = new TimerRunningColorGUI();
        timerPausedColorGUI = new TimerPausedColorGUI();

        challengeGUI = new ChallengeGUI();
        randomChallengesGUI = new RandomChallengesGUI();

        goalsGUI = new GoalsGui();
    }

    public void updateInventories() {
        TimerGuiItems.updateColors();

        timerColorGui.updateInventory();
        timerRunningColorGUI.updateInventory();
        timerPausedColorGUI.updateInventory();

        timerGUI.updateInventory();
        challengeGUI.updateInventory();
        randomChallengesGUI.updateInventory();
    }

    private void initScoreboard() {
        LOGGER.debug("Scoreboard objectives", DebugLevel.LEVEL_2);
        Bukkit.getScheduler().runTask(this, () -> { //Using a Scheduler to avoid nullpointerexceptions, as the scoreboard manager gets loaded after the world, but this plugin gets loaded before the world
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

            healthScoreboard = new HealthScoreboard(this, scoreboard);

            healthScoreboard.createScoreboard();
        });
    }

    //Getter
    public static ChallengesPluginNeo getChallengePlugin() {
        return instance;
    }

    public ChallengeTimer getTimer() {
        return timer;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public FileLoader getFileLoader() {
        return fileLoader;
    }

    public AdvancementHandler getAdvancementHandler() {
        return advancementHandler;
    }
}
