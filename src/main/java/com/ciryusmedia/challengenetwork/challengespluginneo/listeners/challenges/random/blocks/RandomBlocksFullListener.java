package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
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

            plugin.log("Random dropping...", DebugLevelOld.LEVEL_3);
            plugin.log(block.getType().name(), DebugLevelOld.LEVEL_4);
            plugin.log(randomItem.getType().name(), DebugLevelOld.LEVEL_4);
            plugin.log(location.toString(), DebugLevelOld.LEVEL_4);
            plugin.log(world.toString(), DebugLevelOld.LEVEL_4);

            Item droppedItem = world.dropItemNaturally(location, randomItem);

            plugin.log(droppedItem.getItemStack().getType().name(), DebugLevelOld.LEVEL_4);
            plugin.log(droppedItem.getLocation().toString(), DebugLevelOld.LEVEL_4);
            plugin.log(block.getDrops().toString(), DebugLevelOld.LEVEL_4);
        }
    }

    public RandomBlocksFullListener(Challenge challenge) {
        super(challenge);
    }

}
