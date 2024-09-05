package me.opd.gambitremastered.game.mechanics;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class RemoveFireworkDamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if ((e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) && e.getDamageSource() instanceof Firework)) {
            e.setDamage(0);
            //TODO fix this
            e.setCancelled(true);
        }
    }
}
