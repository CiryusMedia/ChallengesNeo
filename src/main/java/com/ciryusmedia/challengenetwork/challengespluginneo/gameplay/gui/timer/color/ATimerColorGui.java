package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class ATimerColorGui extends AGUIListener implements Listener, TimerGuiItems {

    private final boolean running;

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, timerFillerItem, timerLineFillerItem);
        TimerGuiItems.initUpdateColorWoolBlocks();

        int getColorPos = 0;
        for (int i = 0; i < inv.getSize() && getColorPos < timerColorItems.size(); i++) {
            if (!(i < 9 | i % 9 == 0 | i % 9 == 8)) {
                inv.setItem(i, timerColorItems.get(getColorPos).clone(running));
                getColorPos++;
            }
        }

        inv.setItem(inv.getSize() - 1, exitItem);
    }

    public ATimerColorGui(ItemStack fillerItem, ItemStack lineFillerItem, boolean running) {
        super(fillerItem, lineFillerItem);
        this.running = running;
    }
}
