package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.random;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.RandomChallengeGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.InventoryCollection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RandomChallengesGUI extends AGUIListener implements Listener, RandomChallengeGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(randomBlocksLoottable)) {
            player.chat("/challenge random randomblocksloottable " + !plugin.getConfig().getBoolean("RandomBlocksLoottable"));
        } else if (item.equals(randomBlocksFull)) {
            player.chat("/challenge random randomblocksfull " + !plugin.getConfig().getBoolean("RandomBlocksFull"));
        } else if (item.equals(randomMobsLoottable)) {
            player.chat("/challenge random randommobsloottable " + !plugin.getConfig().getBoolean("RandomMobsLoottable"));
        } else if (item.equals(randomMobsFull)) {
            player.chat("/challenge random randommobsfull " + !plugin.getConfig().getBoolean("RandomMobsFull"));
        }
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(InventoryCollection.challengeGUI);
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 3*9, "Random Challenges");

        initRandomChallengeGuiItems();
        updateInventory();
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, challengeFillerItem, challengeLineFillerItem);

        updateRandomChallengeGuiItems();

        inv.setItem(10, Challenge.RANDOM_BLOCKS_LOOTTABLE.menuItem);
        inv.setItem(11, Challenge.RANDOM_BLOCKS_FULL.menuItem);
        inv.setItem(15, Challenge.RANDOM_MOBS_LOOTTABLE.menuItem);
        inv.setItem(16, Challenge.RANDOM_MOBS_FULL.menuItem);
        inv.setItem(inv.getSize() - 1,  exitItem);
    }

    public RandomChallengesGUI() {
        super(challengeFillerItem, challengeLineFillerItem);
        initInv();
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory randomChallengesInv) {
        inv = randomChallengesInv;
    }
}
