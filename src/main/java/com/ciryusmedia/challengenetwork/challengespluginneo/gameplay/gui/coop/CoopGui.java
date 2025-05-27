package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.coop;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.coop.Coop;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.CoopGuiItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CoopGui extends AGUIListener implements CoopGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(Coop.COOP.item)) player.chat("/coop coop " + !Coop.COOP.isEnabled());
        if (item.equals(Coop.FFA.item)) player.chat("/coop ffa " + !Coop.FFA.isEnabled());
//        if (item.equals(Coop.TEAMS.item)) player.chat("/coop teams " + !Coop.TEAMS.isEnabled());
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 3 * 9, "Coop");

        inv.setItem(10, Coop.COOP.item);
        inv.setItem(12, Coop.FFA.item);
//        inv.setItem(14, Coop.TEAMS.item);
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, fillerItem, lineFillerItem);
    }

    public CoopGui() {
        super(coopFillerItem, coopLineFillerItem);
    }
}
