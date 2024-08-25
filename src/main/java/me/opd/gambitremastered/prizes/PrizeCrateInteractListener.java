package me.opd.gambitremastered.prizes;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PrizeCrateInteractListener implements Listener {
    //TODO implement spin animation and give token

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getClickedBlock() == null)
            return;

        Block block = e.getClickedBlock();
        if(!block.getBlockData().getMaterial().equals(Material.ENDER_CHEST))
            return;

        ItemStack item = p.getItemInUse();
        if(item == null || item.getType().equals(Material.AIR))
            return;

        if(item.hasItemMeta() && item.getType().equals(Material.SUNFLOWER)){
            e.setCancelled(true);
            //Summon Firework, give random item, send message
        }
    }

    //TODO Add damage block from firework explosion.
}
