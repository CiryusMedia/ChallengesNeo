package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.random;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.Commands;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges.ChallengeGUI;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.RandomChallengeGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class RandomChallengesGUI extends AGUIListener implements Listener, RandomChallengeGuiItems, Commands {

    public static final RandomChallengesGUI INST = new RandomChallengesGUI();

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(randomBlocksLoottable)) {
            player.chat(CHALLENGE_CMD + " random randomblocksloottable " + !plugin.getConfig().getBoolean(Challenge.RANDOM_BLOCKS_LOOTTABLE.path));
        } else if (item.equals(randomBlocksFull)) {
            player.chat(CHALLENGE_CMD + " random randomblocksfull " + !plugin.getConfig().getBoolean(Challenge.RANDOM_BLOCKS_FULL.path));
        } else if (item.equals(randomMobsLoottable)) {
            player.chat(CHALLENGE_CMD + " random randommobsloottable " + !plugin.getConfig().getBoolean(Challenge.RANDOM_MOBS_LOOTTABLE.path));
        } else if (item.equals(randomMobsFull)) {
            player.chat(CHALLENGE_CMD + " random randommobsfull " + !plugin.getConfig().getBoolean(Challenge.RANDOM_MOBS_FULL.path));
        }
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(ChallengeGUI.INST.getInventory());
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 3*9, "Random Challenges");

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
}
