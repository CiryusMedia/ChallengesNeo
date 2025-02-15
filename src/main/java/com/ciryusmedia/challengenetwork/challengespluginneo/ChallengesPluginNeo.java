package com.ciryusmedia.challengenetwork.challengespluginneo;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.*;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete.ChallengeComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete.DebugComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete.TimerComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections.GeneralGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks.RandomBlocksFullListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks.RandomBlocksLoottableListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities.RandomMobsFullListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities.RandomMobsLoottableListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.synched.InventorySyncListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.challenges.ChallengeGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.challenges.random.RandomChallengesGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.timer.TimerColorInvGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.timer.TimerGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.timer.color.TimerPausedColorGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.timer.color.TimerRunningColorGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.system.BlockBreakListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.system.ChallengeEndListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.system.PlayerJoinLeaveListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ColorOutsourcing;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import com.ciryusmedia.challengenetwork.challengespluginneo.scoreboards.HealthScoreboard;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.fileloaders.WorldLoader;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;

@SuppressWarnings({"DataFlowIssue", "deprecation"})
public final class ChallengesPluginNeo extends JavaPlugin implements PluginMessageListener, WorldLoader {

    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    private static ChallengesPluginNeo instance;

    private ChallengeTimer timer;

    private File randomBlocksLoottableConfigFile;
    private File randomMobsLoottableConfigFile;

    private Scoreboard scoreboard;

    //Inventories
    public static TimerGUI timerGUI;
    public static TimerColorInvGUI timerColorGui;
    public static TimerRunningColorGUI timerRunningColorGUI;
    public static TimerPausedColorGUI timerPausedColorGUI;

    public static ChallengeGUI challengeGUI;
    public static RandomChallengesGUI randomChallengesGUI;

    //Scoreboard Objectives
    HealthScoreboard healthScoreboard;

    //Startup and shutdown
    @Override
    public void onLoad() {
        DEBUGGER.log("Loading Ciryus Challenge Plugin version " + getDescription().getVersion(), DebugLevel.LEVEL_0);

        DEBUGGER.setMessagePrefix(ChatColor.AQUA + "Challenge" + ChatColor.DARK_GRAY + "> ");
        instance = this;

        //Config stuff
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        if (!getConfig().contains("DebugLevel") || getConfig().getInt("DebugLevel") < 0) {
            getConfig().set("DebugLevel", DebugLevel.LEVEL_1.level);
            DEBUGGER.log("Debuglevel not found, setting to \"LEVEL_1\"", DebugLevel.LEVEL_1);
        }
        DEBUGGER.setDebugLevel(getConfig().getInt("DebugLevel"));
        DEBUGGER.log("Debuglevel: " + DEBUGGER.getDebugLevel(), DebugLevel.LEVEL_1);
        saveConfig();
        reloadConfig();

        //Reset world reset
        if (!getConfig().contains("isReset")) {
            getConfig().set("isReset", false);
        }
        if (getConfig().getBoolean("isReset")) {
            resetWorld();
            getConfig().set("Time", 0);
            getConfig().set("isReset", false);
            saveConfig();
        }

        try {
            String dataFolderPath = getDataFolder().getCanonicalPath();
            DEBUGGER.log(dataFolderPath, DebugLevel.LEVEL_5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        createRandomLoottableConfigs();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Messages
        DEBUGGER.log("Enabling Ciryus Challenge Plugin " + getDescription().getVersion(), DebugLevel.LEVEL_0);

        //Bungeecord messenger channels
        DEBUGGER.log("Registering plugin channels", DebugLevel.LEVEL_1);

        DEBUGGER.log("Incoming custom:network", DebugLevel.LEVEL_2);
        getServer().getMessenger().registerIncomingPluginChannel(this, "custom:network", this);

        //Challenge
        DEBUGGER.log("Challenge system", DebugLevel.LEVEL_1);
        DEBUGGER.log("Timer", DebugLevel.LEVEL_2);
        timer = new ChallengeTimer(false, 0);
        timer.setTime(getConfig().getInt("Time"));

        ColorOutsourcing.initColorOutsourcing();
        ChallengeRandomisation.initRandomisation();

        //Initiate and enable
        DEBUGGER.log("Initiating objects", DebugLevel.LEVEL_1);
        initItems();
        initinventories();
        DEBUGGER.log("Enabling plugin logic", DebugLevel.LEVEL_1);
        enableEvents();
        initScoreboard();
        enableCommands();
        enableTabcomplete();

        //Config
        DEBUGGER.log("Loading default config", DebugLevel.LEVEL_2);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Activating tick
        DEBUGGER.log("Starting tick", DebugLevel.LEVEL_1);
        tick();

        //Finished loading
        DEBUGGER.log(ChatColor.RESET + Texts.STARTUP_LOGO, DebugLevel.LEVEL_0);
        DEBUGGER.log("Challenge Plugin Loaded and Enabled", DebugLevel.LEVEL_0);

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
        getConfig().set("Time", timer.getTime());
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
                DEBUGGER.setDebugLevel(getConfig().getInt("DebugLevel"));
                updateInventories();
            }
        }.runTaskTimer(ChallengesPluginNeo.getChallengePlugin(), 20, 20);
    }

    private void createRandomLoottableConfigs() {
        randomBlocksLoottableConfigFile = new File(getDataFolder(), "randomblocksloottablemap.yml");
        randomMobsLoottableConfigFile = new File(getDataFolder(), "randommobsloottablemap.yml");

        if (!randomBlocksLoottableConfigFile.exists()) {
            randomBlocksLoottableConfigFile.getParentFile().mkdirs();
            saveResource("randomblocksloottablemap.yml", false);
        }

        if (!randomMobsLoottableConfigFile.exists()) {
            randomMobsLoottableConfigFile.getParentFile().mkdirs();
            saveResource("randommobsloottablemap.yml", false);
        }
    }

    //Inits and enablers
    private void enableCommands() {
        DEBUGGER.log("Commands", DebugLevel.LEVEL_2);
        getCommand("debug").setExecutor(new DebugCommand());
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("heal").setExecutor(new HealCommand());
    }

    private void enableTabcomplete() {
        DEBUGGER.log("Tabcomplete", DebugLevel.LEVEL_2);
        getCommand("timer").setTabCompleter(new TimerComplete());
        getCommand("challenge").setTabCompleter(new ChallengeComplete());
        getCommand("debug").setTabCompleter(new DebugComplete());
    }

    private void enableEvents() {
        DEBUGGER.log("Events", DebugLevel.LEVEL_2);
        //System
        DEBUGGER.log("System listeners", DebugLevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new ChallengeEndListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        //GUI
        DEBUGGER.log("GUI listeners", DebugLevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(timerGUI, this);
        getServer().getPluginManager().registerEvents(timerColorGui, this);
        getServer().getPluginManager().registerEvents(timerRunningColorGUI, this);
        getServer().getPluginManager().registerEvents(timerPausedColorGUI, this);

        getServer().getPluginManager().registerEvents(challengeGUI, this);
        getServer().getPluginManager().registerEvents(randomChallengesGUI, this);

        //Challenges
        DEBUGGER.log("Challenge listeners", DebugLevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(new RandomBlocksLoottableListener(Challenge.RANDOM_BLOCKS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomBlocksFullListener(Challenge.RANDOM_BLOCKS_FULL), this);
        getServer().getPluginManager().registerEvents(new RandomMobsLoottableListener(Challenge.RANDOM_MOBS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomMobsFullListener(Challenge.RANDOM_MOBS_FULL), this);

        getServer().getPluginManager().registerEvents(new InventorySyncListener(Challenge.INVENTORY_SYNC), this);
    }

    private void initItems() {
        DEBUGGER.log("Items", DebugLevel.LEVEL_2);
        GeneralGuiItems.initGeneralGuiItems();
        TimerGuiItems.initTimerGuiItems();
    }

    private void initinventories() {
        DEBUGGER.log("Inventories", DebugLevel.LEVEL_2);
        timerGUI = new TimerGUI();
        timerColorGui = new TimerColorInvGUI();
        timerRunningColorGUI = new TimerRunningColorGUI();
        timerPausedColorGUI = new TimerPausedColorGUI();

        challengeGUI = new ChallengeGUI();
        randomChallengesGUI = new RandomChallengesGUI();
    }

    public void updateColorInventories() {
        TimerGuiItems.updateColors();

        timerColorGui.updateInventory();
        timerRunningColorGUI.updateInventory();
        timerPausedColorGUI.updateInventory();
    }

    public void updateInventories() {
        updateColorInventories();

        timerGUI.updateInventory();
        challengeGUI.updateInventory();
        randomChallengesGUI.updateInventory();
    }

    private void initScoreboard() {
        DEBUGGER.log("Scoreboard objectives", DebugLevel.LEVEL_2);
        Bukkit.getScheduler().runTask(this, () -> { //Using a Scheduler to avoid nullpointerexceptions, as the scoreboard manager gets loaded after the world, but this plugin get loaded before the world
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

    public File getRandomBlocksLoottableConfigFile() {
        return randomBlocksLoottableConfigFile;
    }

    public File getRandomMobsLoottableConfigFile() {
        return randomMobsLoottableConfigFile;
    }
}
