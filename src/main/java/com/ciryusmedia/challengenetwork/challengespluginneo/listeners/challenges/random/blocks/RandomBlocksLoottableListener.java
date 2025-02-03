package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RandomBlocksLoottableListener extends ARandomBlocks {

    @Override
    public void handleRandomBlocks(Block block, Collection<ItemStack> drops) {
        if (!challenge.isEnabled() || !timer.isRunning()) {
            return;
        }

        instance.log(drops.toString(), Debuglevel.LEVEL_4);

        if (!drops.isEmpty()) {
            instance.log("Random dropping...", Debuglevel.LEVEL_3);
            block.getWorld().dropItemNaturally(
                    block.getLocation(),
                    (ItemStack) YamlConfiguration.loadConfiguration(instance.getRandomBlocksLoottableConfigFile()).get(block.getType().name())
            );
            instance.log(((ItemStack) instance.getRandomBlocksLoottableConfig().get(block.getType().name())).getType().name(), Debuglevel.LEVEL_4);
            instance.log(block.getLocation().toString(), Debuglevel.LEVEL_4);
        }
    }

    public RandomBlocksLoottableListener(Challenge challenge) {
        super(challenge);
    }

}
