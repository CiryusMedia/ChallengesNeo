package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.blocks;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
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
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public abstract class ARandomBlocks extends AChallengeListener implements Listener {

    ChallengeRandomisation rro;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlock();
        boolean badBisectedHalf = false;

        //Shit for blocks with 2 block in volume
        if (timer.isRunning() && challenge.isEnabled()) {
            instance.log(e.getEventName(), Debuglevel.LEVEL_3);
            instance.log(b.translationKey() + " " + b.getType().translationKey() + " "
                    + b.getLocation().getBlockX() + b.getLocation().getBlockY() + b.getLocation().getBlockZ(), Debuglevel.LEVEL_5);
            instance.log(challenge.getDisplayName() + " is " + challenge.isEnabled(), Debuglevel.LEVEL_5);
            instance.log("Timer is " + timer.isRunning(), Debuglevel.LEVEL_5);

            //Code for the "top" part of bisected blocks
            if (b.getBlockData() instanceof Bed bedData) {
                instance.log("Block is a bed", Debuglevel.LEVEL_4);
                instance.log(bedData.getPart().toString(), Debuglevel.LEVEL_4);
                if (bedData.getPart() == Bed.Part.FOOT) {
                    bedData.setPart(Bed.Part.HEAD);
                    b.setBlockData(bedData);
                    badBisectedHalf = true;
                }
            } else if (b.getBlockData() instanceof Bisected bisectedData) {
                instance.log("Block is bisected", Debuglevel.LEVEL_4);
                instance.log(bisectedData.getHalf().toString(), Debuglevel.LEVEL_4);
                if (bisectedData.getHalf() == Bisected.Half.TOP) {
                    badBisectedHalf = true;
                    b.setType(Material.AIR);
                }
            } else {
                instance.log("Block is not bisected", Debuglevel.LEVEL_4);
            }
        } else {
            instance.log(challenge.getDisplayName() + " is " + challenge.isEnabled(), Debuglevel.LEVEL_5);
            instance.log("Timer is " + timer.isRunning(), Debuglevel.LEVEL_5);
        }

        Collection<ItemStack> orgDrops = b.getDrops(p.getInventory().getItemInMainHand());

        if (!orgDrops.isEmpty() && !badBisectedHalf) {
            instance.log(orgDrops.toString(), Debuglevel.LEVEL_3);
            handleRandomBlocks(b, orgDrops);
        } else {
            instance.log("Not dropping...", Debuglevel.LEVEL_3);
        }

        if (challenge.isEnabled())
            e.setDropItems(false); //Cancel drop of the original itemdrops
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {

        Block block = e.getBlock();

        if (challenge.isEnabled()) {
            instance.log(e.getEventName(), Debuglevel.LEVEL_3);
            instance.log(e.getEventName() + " cancelled", Debuglevel.LEVEL_3);
            e.setCancelled(true);

            BlockData blockData = block.getBlockData();
            BlockDestroyEvent blockDestroyEvent = new BlockDestroyEvent(block, blockData, blockData, 0, true); //Sending a new BlockDestroyEvent, it just is easier to handle :)
            blockDestroyEvent.callEvent();

            instance.log("Setting block to air", Debuglevel.LEVEL_3);
            block.setType(Material.AIR);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        float y = e.getYield();
        List<Block> bs = e.blockList();

        if (challenge.isEnabled()
            && (e.getExplosionResult().equals(ExplosionResult.DESTROY) //Only call the code if the explosion actually destroys blocks (unlike the wind charge)
            || e.getExplosionResult().equals(ExplosionResult.DESTROY_WITH_DECAY))) {

            instance.log(e.getEventName(), Debuglevel.LEVEL_3);
            instance.log(String.valueOf(y), Debuglevel.LEVEL_4);
            instance.log(e.getExplosionResult().toString(), Debuglevel.LEVEL_4);
            instance.log(e.getEntityType().toString(), Debuglevel.LEVEL_4);

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

        if (challenge.isEnabled() && timer.isRunning()) {
            instance.log(e.getEventName() + " " + b.getType().name() + " " + b.getType().translationKey() + " "
                    + b.getLocation().getBlockX() + b.getLocation().getBlockY() + b.getLocation().getBlockZ(), Debuglevel.LEVEL_5);
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
        this.rro = instance.getRro();
    }
}
