package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.RandomisationUtils;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RandomBlocksLoottableListener extends ARandomBlocks {

    @Override
    public void handleRandomBlocks(Block block, Collection<ItemStack> drops) {
        if (!challenge.enabled || !timer.isRunning()) {
            return;
        }

        RandomisationUtils.checkInitialized();

        LOGGER.debug(drops.toString(), DebugLevel.LEVEL_4);

        if (!drops.isEmpty()) {
            YamlConfiguration loottable = YamlConfiguration.loadConfiguration(plugin.getFileLoader().getRandomBlocksLoottableConfigFile());
            LOGGER.debug("Random dropping...", DebugLevel.LEVEL_3);
            block.getWorld().dropItemNaturally(
                    block.getLocation(),
                    (ItemStack) loottable.get(block.getType().name())
            );
            LOGGER.debug(((ItemStack) loottable.get(block.getType().name())).getType().name(), DebugLevel.LEVEL_4);
            LOGGER.debug(block.getLocation().toString(), DebugLevel.LEVEL_4);
        }
    }

    public RandomBlocksLoottableListener(Challenge challenge) {
        super(challenge);
    }

}
