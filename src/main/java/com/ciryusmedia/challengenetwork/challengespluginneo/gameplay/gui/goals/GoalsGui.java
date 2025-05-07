package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.goals;

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
        if (item.equals(Goal.KILL_ENDER_DRAGON.item)) handleGoal(Goal.KILL_ENDER_DRAGON);
        else if (item.equals(Goal.GET_ALL_ADVANCEMENTS.item)) handleGoal(Goal.GET_ALL_ADVANCEMENTS);
        else if (item.equals(Goal.PLAYER_DEATH.item)) handleGoal(Goal.PLAYER_DEATH);
    }

    public void handleGoal(Goal goal) {
        if (!goal.isEnabled()) goal.enable();
        else goal.disable();
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9*3, "Goals");
        initGoalGuiItems();
        updateInventory();
    }

    @Override
    public void updateInventory() {
        if (Goal.anyEnabled(GoalType.SUCCESS)) {
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
