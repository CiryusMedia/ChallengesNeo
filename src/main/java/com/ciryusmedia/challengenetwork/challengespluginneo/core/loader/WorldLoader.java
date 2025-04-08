package com.ciryusmedia.challengenetwork.challengespluginneo.core.loader;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public interface WorldLoader {

    ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    //Reset Stuff
    default void resetWorld() {
        LOGGER.debug("Resetting world files", DebugLevel.LEVEL_1);
        try {
            File world = new File(Bukkit.getWorldContainer(), "world");
            File world_nether = new File(Bukkit.getWorldContainer(), "world_nether");
            File world_the_end = new File(Bukkit.getWorldContainer(), "world_the_end");

            LOGGER.debug("Deleting world files", DebugLevel.LEVEL_2);
            deleteWorldFiles(world);
            LOGGER.debug("Deleting world_nether files", DebugLevel.LEVEL_2);
            deleteWorldFiles(world_nether);
            LOGGER.debug("Deleting world_the_end files", DebugLevel.LEVEL_2);
            deleteWorldFiles(world_the_end);

            LOGGER.debug("Making world files", DebugLevel.LEVEL_2);
            makeWorldFiles(world);
            LOGGER.debug("Making world_nether files", DebugLevel.LEVEL_2);
            makeWorldFiles(world_nether);
            LOGGER.debug("Making world_the_end files", DebugLevel.LEVEL_2);
            makeWorldFiles(world_the_end);
        } catch (IOException e) {
            LOGGER.error("Could not reset world files! Cause: " + Arrays.toString(e.getStackTrace()));
        }
    }

    default void deleteWorldFiles(File worldFolder) throws IOException {
        Files.walk(worldFolder.toPath())
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    default void makeWorldFiles(File worldFolder) {
        worldFolder.mkdirs();
        new File(worldFolder, "data").mkdirs();
        new File(worldFolder, "datapacks").mkdirs();
        new File(worldFolder, "playerdata").mkdirs();
        new File(worldFolder, "poi").mkdirs();
        new File(worldFolder, "region").mkdirs();
    }
}
