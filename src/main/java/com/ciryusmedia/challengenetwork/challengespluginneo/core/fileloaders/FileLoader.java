package com.ciryusmedia.challengenetwork.challengespluginneo.core.fileloaders;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;

import java.io.File;

public class FileLoader {

    ChallengesPluginNeo plugin;

    private File randomBlocksLoottableConfigFile;
    private File randomMobsLoottableConfigFile;

    private void createRandomLoottableConfigs() {
        randomBlocksLoottableConfigFile = new File(plugin.getDataFolder(), "randomblocksloottablemap.yml");
        randomMobsLoottableConfigFile = new File(plugin.getDataFolder(), "randommobsloottablemap.yml");

        if (!randomBlocksLoottableConfigFile.exists()) {
            randomBlocksLoottableConfigFile.getParentFile().mkdirs();
            plugin.saveResource("randomblocksloottablemap.yml", false);
        }

        if (!randomMobsLoottableConfigFile.exists()) {
            randomMobsLoottableConfigFile.getParentFile().mkdirs();
            plugin.saveResource("randommobsloottablemap.yml", false);
        }
    }

    public FileLoader(ChallengesPluginNeo plugin) {
        this.plugin = plugin;

        createRandomLoottableConfigs();
    }

    public File getRandomBlocksLoottableConfigFile() {
        return randomBlocksLoottableConfigFile;
    }

    public File getRandomMobsLoottableConfigFile() {
        return randomMobsLoottableConfigFile;
    }

}
