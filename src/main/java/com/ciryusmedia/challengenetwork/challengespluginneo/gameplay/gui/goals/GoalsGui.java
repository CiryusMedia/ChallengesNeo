package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.goals;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.GoalType;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.GoalsGuiItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GoalsGui extends AGUIListener implements GoalsGuiItems {

    public GoalsGui() {
        super(goalLineFillerItem, goalFillerItem);
    }

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(Goal.KILL_ENDER_DRAGON.item)) handleGoal(Goal.KILL_ENDER_DRAGON, player);
        else if (item.equals(Goal.GET_ALL_ADVANCEMENTS.item)) handleGoal(Goal.GET_ALL_ADVANCEMENTS, player);
        else if (item.equals(Goal.PLAYER_DEATH.item)) handleGoal(Goal.PLAYER_DEATH, player);
    }

    public void handleGoal(Goal goal, Player player) {
        player.chat("/goal " + goal.key + (goal.isEnabled() ? " off" : " on"));
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9*3, "Goals");
        initGoalGuiItems();
        updateInventory();
    }

    @Override
    public void updateInventory() {
        ChallengeLogger.getLogger().log("Goals updated");
        if (Goal.anyEnabled(GoalType.SUCCESS)) { //TODO: Doens't work yet
            goalExit.setItemMeta(goalExitDefaultMeta);
        } else {
            goalExit.setItemMeta(goalExitWarningMeta);
        }

        emptyInventoryItemFiller(inv, goalFillerItem, goalLineFillerItem);

        inv.setItem(9 + 1, Goal.KILL_ENDER_DRAGON.item);
        inv.setItem(9 + 3, Goal.GET_ALL_ADVANCEMENTS.item);
        inv.setItem(9 + 6, Goal.PLAYER_DEATH.item);
        inv.setItem(inv.getSize() - 1, goalExit);
    }
}
