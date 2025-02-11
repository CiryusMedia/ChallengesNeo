package com.ciryusmedia.challengenetwork.challengespluginneo;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.*;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete.ChallengeComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete.DebugComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete.TimerComplete;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Texts;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings({"DataFlowIssue", "deprecation"})
public final class ChallengesPluginNeo extends JavaPlugin implements PluginMessageListener {

    public static int debugLevel;

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
        log("Loading Ciryus Challenge Plugin version " + getDescription().getVersion(), Debuglevel.LEVEL_0);

        instance = this;

        //Config stuff
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        if (!getConfig().contains("DebugLevel") || getConfig().getInt("DebugLevel") < 0) {
            getConfig().set("DebugLevel", Debuglevel.LEVEL_1);
            log("Debuglevel not found, setting to \"LEVEL_1\"", Debuglevel.LEVEL_1);
        }
        debugLevel = getConfig().getInt("DebugLevel");
        log("Debuglevel: " + debugLevel, Debuglevel.LEVEL_1);
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
            log(dataFolderPath, Debuglevel.LEVEL_5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        createRandomLoottableConfigs();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Messages
        log("Enabling Ciryus Challenge Plugin " + getDescription().getVersion(), Debuglevel.LEVEL_0);

        //Bungeecord messenger channels
        log("Registering plugin channels", Debuglevel.LEVEL_1);

        log("Incoming custom:network", Debuglevel.LEVEL_2);
        getServer().getMessenger().registerIncomingPluginChannel(this, "custom:network", this);

        //Challenge
        log("Challenge system", Debuglevel.LEVEL_1);
        log("Timer", Debuglevel.LEVEL_2);
        timer = new ChallengeTimer(false, 0);
        timer.setTime(getConfig().getInt("Time"));

        ColorOutsourcing.initColorOutsourcing();
        ChallengeRandomisation.initRandomisation();

        //Initiate and enable
        log("Initiating objects", Debuglevel.LEVEL_1);
        initItems();
        initinventories();
        log("Enabling plugin logic", Debuglevel.LEVEL_1);
        enableEvents();
        initScoreboard();
        enableCommands();
        enableTabcomplete();

        //Config
        log("Loading default config", Debuglevel.LEVEL_2);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Activating tick
        log("Starting tick", Debuglevel.LEVEL_1);
        tick();

        //Finished loading
        log(ChatColor.RESET + Texts.STARTUP_LOGO, Debuglevel.LEVEL_0);
        log("Challenge Plugin Loaded and Enabled", Debuglevel.LEVEL_0);

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

    public void log(String message, int messageDebuglevel) {
        ChatColor color = Debuglevel.debugColor(messageDebuglevel);
        if (messageDebuglevel <= debugLevel) {
            getServer().getConsoleSender().sendMessage(Texts.PREFIX + color + message);
        }
    }

    private void tick() {
        new BukkitRunnable() {
            @Override
            public void run() {
                reloadConfig();
                debugLevel = getConfig().getInt("DebugLevel");
                updateInventories();
            }
        }.runTaskTimer(ChallengesPluginNeo.getChallengePlugin(), 20, 20);
    }

    private void resetWorld() {
        log("Resetting world files", Debuglevel.LEVEL_1);
        try {
            File world = new File(Bukkit.getWorldContainer(), "world");
            File world_nether = new File(Bukkit.getWorldContainer(), "world_nether");
            File world_the_end = new File(Bukkit.getWorldContainer(), "world_the_end");

            log("Deleting world files", Debuglevel.LEVEL_2);
            deleteWorldFiles(world);
            log("Deleting world_nether files", Debuglevel.LEVEL_2);
            deleteWorldFiles(world_nether);
            log("Deleting world_the_end files", Debuglevel.LEVEL_2);
            deleteWorldFiles(world_the_end);

            log("Making world files", Debuglevel.LEVEL_2);
            makeWorldFiles(world);
            log("Making world_nether files", Debuglevel.LEVEL_2);
            makeWorldFiles(world_nether);
            log("Making world_the_end files", Debuglevel.LEVEL_2);
            makeWorldFiles(world_the_end);
        } catch (IOException e) {
            log(ChatColor.RED + "Could not reset world files! Cause: " + Arrays.toString(e.getStackTrace()), Debuglevel.LEVEL_0);
        }
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

    //Reset Stuff
    void deleteWorldFiles(File worldFolder) throws IOException {
        Files.walk(worldFolder.toPath())
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    void makeWorldFiles(File worldFolder) {
        worldFolder.mkdirs();
        new File(worldFolder, "data").mkdirs();
        new File(worldFolder, "datapacks").mkdirs();
        new File(worldFolder, "playerdata").mkdirs();
        new File(worldFolder, "poi").mkdirs();
        new File(worldFolder, "region").mkdirs();
    }

    //Inits and enablers
    private void enableCommands() {
        log("Commands", Debuglevel.LEVEL_2);
        getCommand("debug").setExecutor(new DebugCommand());
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("heal").setExecutor(new HealCommand());
    }

    private void enableTabcomplete() {
        log("Tabcomplete", Debuglevel.LEVEL_2);
        getCommand("timer").setTabCompleter(new TimerComplete());
        getCommand("challenge").setTabCompleter(new ChallengeComplete());
        getCommand("debug").setTabCompleter(new DebugComplete());
    }

    private void enableEvents() {
        log("Events", Debuglevel.LEVEL_2);
        //System
        log("System listeners", Debuglevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new ChallengeEndListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        //GUI
        log("GUI listeners", Debuglevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(timerGUI, this);
        getServer().getPluginManager().registerEvents(timerColorGui, this);
        getServer().getPluginManager().registerEvents(timerRunningColorGUI, this);
        getServer().getPluginManager().registerEvents(timerPausedColorGUI, this);

        getServer().getPluginManager().registerEvents(challengeGUI, this);
        getServer().getPluginManager().registerEvents(randomChallengesGUI, this);

        //Challenges
        log("Challenge listeners", Debuglevel.LEVEL_2);
        getServer().getPluginManager().registerEvents(new RandomBlocksLoottableListener(Challenge.RANDOM_BLOCKS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomBlocksFullListener(Challenge.RANDOM_BLOCKS_FULL), this);
        getServer().getPluginManager().registerEvents(new RandomMobsLoottableListener(Challenge.RANDOM_MOBS_LOOTTABLE), this);
        getServer().getPluginManager().registerEvents(new RandomMobsFullListener(Challenge.RANDOM_MOBS_FULL), this);

        getServer().getPluginManager().registerEvents(new InventorySyncListener(Challenge.INVENTORY_SYNC), this);
    }

    private void initItems() {
        log("Items", Debuglevel.LEVEL_2);
        GeneralGuiItems.initGeneralGuiItems();
        TimerGuiItems.initTimerGuiItems();
    }

    private void initinventories() {
        log("Inventories", Debuglevel.LEVEL_2);
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
        log("Scoreboard objectives", Debuglevel.LEVEL_2);
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
