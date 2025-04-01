package com.ciryusmedia.challengenetwork.challengespluginneo;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.loader.FileLoader;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.*;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.ChallengeComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.DebugComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.tabcomplete.TimerComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.GeneralGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks.RandomBlocksFullListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks.RandomBlocksLoottableListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities.RandomMobsFullListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities.RandomMobsLoottableListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.synched.InventorySyncListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.ChallengeGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.random.RandomChallengesGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.TimerColorInvGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.TimerGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color.TimerPausedColorGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color.TimerRunningColorGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system.BlockBreakListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system.ChallengeEndListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system.PlayerJoinLeaveListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.RandomisationUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.scoreboards.HealthScoreboard;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.loader.WorldLoader;
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
public final class ChallengesPluginNeo extends JavaPlugin implements PluginMessageListener, WorldLoader {

    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    private static ChallengesPluginNeo instance;

    private ChallengeTimer timer;
    private FileLoader fileLoader;

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
        DEBUGGER.log("Loading Ciryus Challenge Plugin version " + getDescription().getVersion());

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
            saveConfig();
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

        fileLoader = new FileLoader(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Messages
        DEBUGGER.log("Enabling Ciryus Challenge Plugin " + getDescription().getVersion());

        //Bungeecord messenger channels
        DEBUGGER.log("Registering plugin channels", DebugLevel.LEVEL_1);

        DEBUGGER.log("Incoming custom:network", DebugLevel.LEVEL_2);
        getServer().getMessenger().registerIncomingPluginChannel(this, "custom:network", this);

        //Challenge
        DEBUGGER.log("Challenge system", DebugLevel.LEVEL_1);
        DEBUGGER.log("Timer", DebugLevel.LEVEL_2);
        timer = new ChallengeTimer(false, 0);
        timer.setTime(getConfig().getInt("Time"));

        RandomisationUtils.initRandomisation();

        //Initiate and enable
        DEBUGGER.log("Initiating objects", DebugLevel.LEVEL_1);
        initItems();
        initInventories();

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
        DEBUGGER.log(ChatColor.RESET + Texts.STARTUP_LOGO);
        DEBUGGER.log("Challenge Plugin Loaded and Enabled");

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
        //Random Challenges
        getServer().getPluginManager().registerEvents(new RandomBlocksLoottableListener(Challenge.RANDOM_BLOCKS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomBlocksFullListener(Challenge.RANDOM_BLOCKS_FULL), this);
        getServer().getPluginManager().registerEvents(new RandomMobsLoottableListener(Challenge.RANDOM_MOBS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomMobsFullListener(Challenge.RANDOM_MOBS_FULL), this);

        //Sync Challenges
        getServer().getPluginManager().registerEvents(new InventorySyncListener(Challenge.INVENTORY_SYNC), this);
    }

    private void initItems() {
        DEBUGGER.log("Items", DebugLevel.LEVEL_2);
        GeneralGuiItems.initGeneralGuiItems();
        TimerGuiItems.initTimerGuiItems();
    }

    private void initInventories() {
        DEBUGGER.log("Inventories", DebugLevel.LEVEL_2);
        timerGUI = new TimerGUI();
        timerColorGui = new TimerColorInvGUI();
        timerRunningColorGUI = new TimerRunningColorGUI();
        timerPausedColorGUI = new TimerPausedColorGUI();

        challengeGUI = new ChallengeGUI();
        randomChallengesGUI = new RandomChallengesGUI();
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
        DEBUGGER.log("Scoreboard objectives", DebugLevel.LEVEL_2);
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

}
