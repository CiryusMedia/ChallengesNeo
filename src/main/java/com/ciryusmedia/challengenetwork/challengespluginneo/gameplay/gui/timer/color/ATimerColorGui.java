package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class ATimerColorGui extends AGUIListener implements Listener, TimerGuiItems {

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, timerFillerItem, timerLineFillerItem);
        TimerGuiItems.initUpdateColorWoolBlocks();

        int getColorPos = 0;
        for (int i = 0; i < inv.getSize() && getColorPos < runningColorItems.size(); i++) {
            if (!(i < 9 | i % 9 == 0 | i % 9 == 8)) {
                inv.setItem(i, runningColorItems.get(getColorPos));
                getColorPos++;
            }
        }

        inv.setItem(inv.getSize() - 1, exitItem);
    }

    public ATimerColorGui(ItemStack fillerItem, ItemStack lineFillerItem) {
        super(fillerItem, lineFillerItem);
    }
}
