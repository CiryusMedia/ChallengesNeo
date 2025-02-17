package com.ciryusmedia.challengenetwork.challengespluginneo.fileloaders;

import com.ciryusmedia.challengenetwork.challengespluginneo.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.DebugLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public interface WorldLoader {

    ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    //Reset Stuff
    default void resetWorld() {
        DEBUGGER.log("Resetting world files", DebugLevel.LEVEL_1);
        try {
            File world = new File(Bukkit.getWorldContainer(), "world");
            File world_nether = new File(Bukkit.getWorldContainer(), "world_nether");
            File world_the_end = new File(Bukkit.getWorldContainer(), "world_the_end");

            DEBUGGER.log("Deleting world files", DebugLevel.LEVEL_2);
            deleteWorldFiles(world);
            DEBUGGER.log("Deleting world_nether files", DebugLevel.LEVEL_2);
            deleteWorldFiles(world_nether);
            DEBUGGER.log("Deleting world_the_end files", DebugLevel.LEVEL_2);
            deleteWorldFiles(world_the_end);

            DEBUGGER.log("Making world files", DebugLevel.LEVEL_2);
            makeWorldFiles(world);
            DEBUGGER.log("Making world_nether files", DebugLevel.LEVEL_2);
            makeWorldFiles(world_nether);
            DEBUGGER.log("Making world_the_end files", DebugLevel.LEVEL_2);
            makeWorldFiles(world_the_end);
        } catch (IOException e) {
            DEBUGGER.log(ChatColor.RED + "Could not reset world files! Cause: " + Arrays.toString(e.getStackTrace()), DebugLevel.LEVEL_0);
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
