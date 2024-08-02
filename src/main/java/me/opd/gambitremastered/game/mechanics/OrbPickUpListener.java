package me.opd.gambitremastered.game.mechanics;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class OrbPickUpListener implements Listener {

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getEntity();
        Item i = e.getItem();

        int in = i.getItemStack().getAmount();
        if (!(i.getItemStack().getType() == Material.NETHER_STAR)) {
            return;
        }
        if (p.getLevel() == 15) {
            p.sendMessage(ChatColor.RED + "You are already carrying the maximum number of motes!");
            i.setPickupDelay(20 * 10);
            e.setCancelled(true);
            return;
        }
        
        p.setExp((float) 1);
        p.giveExpLevels(in);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        p.spawnParticle(Particle.CLOUD, p.getLocation(), 100);

    }
}
