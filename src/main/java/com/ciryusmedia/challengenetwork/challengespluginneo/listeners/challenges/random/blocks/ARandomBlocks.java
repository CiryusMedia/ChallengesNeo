package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.ExplosionResult;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public abstract class ARandomBlocks extends AChallengeListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlock();
        boolean badBisectedHalf = false;

        //Shit for blocks with 2 block in volume
        if (timer.isRunning() && challenge.enabled) {
            plugin.log(e.getEventName(), DebugLevelOld.LEVEL_3);
            plugin.log(b.translationKey() + " " + b.getType().translationKey() + " "
                    + b.getLocation().getBlockX() + b.getLocation().getBlockY() + b.getLocation().getBlockZ(), DebugLevelOld.LEVEL_5);
            plugin.log(challenge.displayName + " is " + challenge.enabled, DebugLevelOld.LEVEL_5);
            plugin.log("Timer is " + timer.isRunning(), DebugLevelOld.LEVEL_5);

            //Code for the "top" part of bisected blocks
            if (b.getBlockData() instanceof Bed bedData) {
                plugin.log("Block is a bed", DebugLevelOld.LEVEL_4);
                plugin.log(bedData.getPart().toString(), DebugLevelOld.LEVEL_4);
                if (bedData.getPart() == Bed.Part.FOOT) {
                    bedData.setPart(Bed.Part.HEAD);
                    b.setBlockData(bedData);
                    badBisectedHalf = true;
                }
            } else if (b.getBlockData() instanceof Bisected bisectedData) {
                plugin.log("Block is bisected", DebugLevelOld.LEVEL_4);
                plugin.log(bisectedData.getHalf().toString(), DebugLevelOld.LEVEL_4);
                if (bisectedData.getHalf() == Bisected.Half.TOP) {
                    badBisectedHalf = true;
                    b.setType(Material.AIR);
                }
            } else {
                plugin.log("Block is not bisected", DebugLevelOld.LEVEL_4);
            }
        } else {
            plugin.log(challenge.displayName + " is " + challenge.displayName, DebugLevelOld.LEVEL_5);
            plugin.log("Timer is " + timer.isRunning(), DebugLevelOld.LEVEL_5);
        }

        Collection<ItemStack> orgDrops = b.getDrops(p.getInventory().getItemInMainHand());

        if (!orgDrops.isEmpty() && !badBisectedHalf) {
            plugin.log(orgDrops.toString(), DebugLevelOld.LEVEL_3);
            handleRandomBlocks(b, orgDrops);
        } else {
            plugin.log("Not dropping...", DebugLevelOld.LEVEL_3);
        }

        if (challenge.enabled)
            e.setDropItems(false); //Cancel drop of the original itemdrops
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {

        Block block = e.getBlock();

        if (challenge.enabled) {
            plugin.log(e.getEventName(), DebugLevelOld.LEVEL_3);
            plugin.log(e.getEventName() + " cancelled", DebugLevelOld.LEVEL_3);
            e.setCancelled(true);

            BlockData blockData = block.getBlockData();
            BlockDestroyEvent blockDestroyEvent = new BlockDestroyEvent(block, blockData, blockData, 0, true); //Sending a new BlockDestroyEvent, it just is easier to handle :)
            blockDestroyEvent.callEvent();

            plugin.log("Setting block to air", DebugLevelOld.LEVEL_3);
            block.setType(Material.AIR);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        float y = e.getYield();
        List<Block> bs = e.blockList();

        if (challenge.enabled
            && (e.getExplosionResult().equals(ExplosionResult.DESTROY) //Only call the code if the explosion actually destroys blocks (unlike the wind charge)
            || e.getExplosionResult().equals(ExplosionResult.DESTROY_WITH_DECAY))) {

            plugin.log(e.getEventName(), DebugLevelOld.LEVEL_3);
            plugin.log(String.valueOf(y), DebugLevelOld.LEVEL_4);
            plugin.log(e.getExplosionResult().toString(), DebugLevelOld.LEVEL_4);
            plugin.log(e.getEntityType().toString(), DebugLevelOld.LEVEL_4);

            for (Block b : bs) {
                Random r = new Random();
                if (r.nextInt(5) < y) //Blocks drop with a 5:yield chance
                    handleRandomBlocks(b);
                e.setYield(0); //Cancel drop of the original itemdrops (setting the yield to 0 so that the original drops have a 0% chance of dropping)
            }
        }
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent e) {
        Block b = e.getBlock();

        if (challenge.enabled && timer.isRunning()) {
            plugin.log(e.getEventName() + " " + b.getType().name() + " " + b.getType().translationKey() + " "
                    + b.getLocation().getBlockX() + b.getLocation().getBlockY() + b.getLocation().getBlockZ(), DebugLevelOld.LEVEL_5);
            e.setWillDrop(false); //Cancel drop of the original itemdrops
        }

        handleRandomBlocks(b);
    }

    public abstract void handleRandomBlocks(Block block, Collection<ItemStack> drops);

    public void handleRandomBlocks(Block block) {
        handleRandomBlocks(block, block.getDrops());
    }

    public ARandomBlocks(Challenge challenge) {
        super(challenge);
    }
}
