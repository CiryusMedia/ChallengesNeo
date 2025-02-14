package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RandomBlocksFullListener extends ARandomBlocks {

    @Override
    public void handleRandomBlocks(Block block, Collection<ItemStack> drops) {
        if (!challenge.enabled || !timer.isRunning()) {
            return;
        }

        ChallengeRandomisation.checkInitialized();

        if (!drops.isEmpty()) {
            ItemStack randomItem = ChallengeRandomisation.getRandomItem();
            Location location = block.getLocation();
            World world = block.getWorld();

            DEBUGGER.log("Random dropping...", DebugLevel.LEVEL_3);
            DEBUGGER.log(block.getType().name(), DebugLevel.LEVEL_4);
            DEBUGGER.log(randomItem.getType().name(), DebugLevel.LEVEL_4);
            DEBUGGER.log(location.toString(), DebugLevel.LEVEL_4);
            DEBUGGER.log(world.toString(), DebugLevel.LEVEL_4);

            Item droppedItem = world.dropItemNaturally(location, randomItem);

            DEBUGGER.log(droppedItem.getItemStack().getType().name(), DebugLevel.LEVEL_4);
            DEBUGGER.log(droppedItem.getLocation().toString(), DebugLevel.LEVEL_4);
            DEBUGGER.log(block.getDrops().toString(), DebugLevel.LEVEL_4);
        }
    }

    public RandomBlocksFullListener(Challenge challenge) {
        super(challenge);
    }

}
