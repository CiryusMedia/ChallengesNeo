package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.goals;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.coop.Coop;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.GoalType;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.GoalsGuiItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GoalsGui extends AGUIListener implements GoalsGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(Goal.KILL_ENDER_DRAGON.item)) handleGoal(Goal.KILL_ENDER_DRAGON, player); //TODO do it with !goal.isEnabled
        else if (item.equals(Goal.GET_ALL_ADVANCEMENTS.item)) handleGoal(Goal.GET_ALL_ADVANCEMENTS, player);
        else if (item.equals(Goal.PLAYER_DEATH.item)) {
            player.chat("/coop ffa false");
            player.chat("/coop coop true");
            handleGoal(Goal.PLAYER_DEATH, player);
        }

        else if (item.equals(goalExitWarning)) player.closeInventory();
    }

    public void handleGoal(Goal goal, Player player) {
        player.chat("/goal " + goal.key + (goal.isEnabled() ? " off" : " on"));
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9*3, "Goals");
        updateInventory();
    }

    @Override
    public void updateInventory() {
        ChallengeLogger.getLogger().log("Goals updated");
        emptyInventoryItemFiller(inv, goalFillerItem, goalLineFillerItem);

        inv.setItem(9 + 1, Goal.KILL_ENDER_DRAGON.item);
        inv.setItem(9 + 3, Goal.GET_ALL_ADVANCEMENTS.item);
        inv.setItem(9 + 6, Goal.PLAYER_DEATH.item);
        if (Goal.anyEnabled(GoalType.SUCCESS)) {
            inv.setItem(inv.getSize() - 1, exitItem);
        } else {
            inv.setItem(inv.getSize() - 1, goalExitWarning);
        }
    }

    public GoalsGui() {
        super(goalLineFillerItem, goalFillerItem);
    }

}
