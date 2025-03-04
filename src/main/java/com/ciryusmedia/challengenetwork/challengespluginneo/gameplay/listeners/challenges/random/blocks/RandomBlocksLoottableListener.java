package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ChallengeRandomisation;
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

        ChallengeRandomisation.checkInitialized();

        DEBUGGER.log(drops.toString(), DebugLevel.LEVEL_4);

        if (!drops.isEmpty()) {
            YamlConfiguration loottable = YamlConfiguration.loadConfiguration(plugin.getFileLoader().getRandomBlocksLoottableConfigFile());
            DEBUGGER.log("Random dropping...", DebugLevel.LEVEL_3);
            block.getWorld().dropItemNaturally(
                    block.getLocation(),
                    (ItemStack) loottable.get(block.getType().name())
            );
            DEBUGGER.log(((ItemStack) loottable.get(block.getType().name())).getType().name(), DebugLevel.LEVEL_4);
            DEBUGGER.log(block.getLocation().toString(), DebugLevel.LEVEL_4);
        }
    }

    public RandomBlocksLoottableListener(Challenge challenge) {
        super(challenge);
    }

}
