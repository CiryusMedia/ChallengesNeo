package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.RandomisationUtils;
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

        RandomisationUtils.checkInitialized();

        if (!drops.isEmpty()) {
            ItemStack randomItem = RandomisationUtils.getRandomItem();
            Location location = block.getLocation();
            World world = block.getWorld();

            LOGGER.debug("Random dropping...", DebugLevel.LEVEL_3);
            LOGGER.debug(block.getType().name(), DebugLevel.LEVEL_4);
            LOGGER.debug(randomItem.getType().name(), DebugLevel.LEVEL_4);
            LOGGER.debug(location.toString(), DebugLevel.LEVEL_4);
            LOGGER.debug(world.toString(), DebugLevel.LEVEL_4);

            Item droppedItem = world.dropItemNaturally(location, randomItem);

            LOGGER.debug(droppedItem.getItemStack().getType().name(), DebugLevel.LEVEL_4);
            LOGGER.debug(droppedItem.getLocation().toString(), DebugLevel.LEVEL_4);
            LOGGER.debug(block.getDrops().toString(), DebugLevel.LEVEL_4);
        }
    }

    public RandomBlocksFullListener(Challenge challenge) {
        super(challenge);
    }

}
