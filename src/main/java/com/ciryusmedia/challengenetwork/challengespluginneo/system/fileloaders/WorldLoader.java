package com.ciryusmedia.challengenetwork.challengespluginneo.system.fileloaders;

import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public interface WorldLoader {

    //Reset Stuff
    default void deleteWorldFiles(File worldFolder) throws IOException {
        Files.walk(worldFolder.toPath())
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    default void resetWorld() {
        log("Resetting world files", DebugLevelOld.LEVEL_1);
        try {
            File world = new File(Bukkit.getWorldContainer(), "world");
            File world_nether = new File(Bukkit.getWorldContainer(), "world_nether");
            File world_the_end = new File(Bukkit.getWorldContainer(), "world_the_end");

            log("Deleting world files", DebugLevelOld.LEVEL_2);
            deleteWorldFiles(world);
            log("Deleting world_nether files", DebugLevelOld.LEVEL_2);
            deleteWorldFiles(world_nether);
            log("Deleting world_the_end files", DebugLevelOld.LEVEL_2);
            deleteWorldFiles(world_the_end);

            log("Making world files", DebugLevelOld.LEVEL_2);
            makeWorldFiles(world);
            log("Making world_nether files", DebugLevelOld.LEVEL_2);
            makeWorldFiles(world_nether);
            log("Making world_the_end files", DebugLevelOld.LEVEL_2);
            makeWorldFiles(world_the_end);
        } catch (IOException e) {
            log(ChatColor.RED + "Could not reset world files! Cause: " + Arrays.toString(e.getStackTrace()), DebugLevelOld.LEVEL_0);
        }
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
