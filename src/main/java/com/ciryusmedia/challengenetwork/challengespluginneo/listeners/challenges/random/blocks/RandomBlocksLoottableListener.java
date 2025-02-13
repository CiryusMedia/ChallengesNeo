package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
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

        plugin.log(drops.toString(), DebugLevelOld.LEVEL_4);

        if (!drops.isEmpty()) {
            YamlConfiguration loottable = YamlConfiguration.loadConfiguration(plugin.getRandomBlocksLoottableConfigFile());
            plugin.log("Random dropping...", DebugLevelOld.LEVEL_3);
            block.getWorld().dropItemNaturally(
                    block.getLocation(),
                    (ItemStack) loottable.get(block.getType().name())
            );
            plugin.log(((ItemStack) loottable.get(block.getType().name())).getType().name(), DebugLevelOld.LEVEL_4);
            plugin.log(block.getLocation().toString(), DebugLevelOld.LEVEL_4);
        }
    }

    public RandomBlocksLoottableListener(Challenge challenge) {
        super(challenge);
    }

}
