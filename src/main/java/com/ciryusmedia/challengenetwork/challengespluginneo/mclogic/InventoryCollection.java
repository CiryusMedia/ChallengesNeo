package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.inventory.Inventory;

public abstract class InventoryCollection {
    public static final Inventory timerGUI = ChallengesPluginNeo.timerGUI.getInventory();
    public static final Inventory timerColorGui = ChallengesPluginNeo.timerColorGui.getInventory();
    public static final Inventory timerRunningColorGUI = ChallengesPluginNeo.timerRunningColorGUI.getInventory();
    public static final Inventory timerPausedColorGUI = ChallengesPluginNeo.timerPausedColorGUI.getInventory();

    public static final Inventory challengeGUI = ChallengesPluginNeo.challengeGUI.getInventory();
    public static final Inventory randomChallengesGUI = ChallengesPluginNeo.randomChallengesGUI.getInventory();
}
