package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.comp;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.Commands;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.comp.Comp;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.CompGuiItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CompGui extends AGUIListener implements CompGuiItems, Commands {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(Comp.COOP.item)) player.chat(COMP_CMD + " coop " + !Comp.COOP.isEnabled());
        if (item.equals(Comp.FFA.item)) {
            player.chat(GOALS_CMD + " " + Goal.PLAYER_DEATH.key + " false");
            player.chat(COMP_CMD + " ffa " + !Comp.FFA.isEnabled());
        }
//        if (item.equals(Coop.TEAMS.item)) player.chat(COMP_CMD + " teams " + !Coop.TEAMS.isEnabled());
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 3 * 9, "Comp");

        inv.setItem(10, Comp.COOP.item);
        inv.setItem(12, Comp.FFA.item);
//        inv.setItem(14, Comp.TEAMS.item);
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, fillerItem, lineFillerItem);
    }

    public CompGui() {
        super(compFillerItem, compLineFillerItem);
    }
}
