package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.ChallengeGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ChallengeGUI extends AGUIListener implements Listener, ChallengeGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(randomChallenges))
            player.chat("/challenge random");
        else if (item.equals(inventorySync))
            player.chat("/challenge sync inventorysync " + !plugin.getConfig().getBoolean("InventorySync"));
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

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory challengeInv) {
        inv = challengeInv;
    }
}
