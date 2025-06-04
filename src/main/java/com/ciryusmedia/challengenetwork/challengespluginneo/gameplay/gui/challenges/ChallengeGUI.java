package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.Commands;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.ChallengeGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ChallengeGUI extends AGUIListener implements Listener, ChallengeGuiItems, Commands {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(randomChallenges)) player.chat(CHALLENGE_CMD + " random");
        else if (item.equals(inventorySync)) player.chat(CHALLENGE_CMD + " sync inventorysync " + !plugin.getConfig().getBoolean(Challenge.INVENTORY_SYNC.path)); //TODO do it with !challenge.isEnabled
        else if (item.equals(crafting_recipe)) player.chat(CHALLENGE_CMD + " misc craftingrecipe " + !plugin.getConfig().getBoolean(Challenge.CRAFTING_RECIPE.path));
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9*3, "Challenges");
        initChallengeGuiItems();
        updateInventory();
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, fillerItem, lineFillerItem);

        inv.setItem(10, randomChallenges);
        inv.setItem(12, inventorySync);
        inv.setItem(14, crafting_recipe);
        inv.setItem(inv.getSize() -1, exitItem);
    }

    public ChallengeGUI() {
        super(ChallengeGuiItems.challengeFillerItem, ChallengeGuiItems.challengeLineFillerItem);
        run();
    }

    private void run() {
        Random random = new Random();
        new BukkitRunnable() {
            @Override
            public void run() {

                randomChallenges.setType(randomChallengeMaterials[random.nextInt(randomChallengeMaterials.length)]);
                updateInventory();

            }
        }.runTaskTimer(ChallengesPluginNeo.getChallengePlugin(), 20, 20);
    }
}
