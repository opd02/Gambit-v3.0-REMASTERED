package me.opd.gambitremastered.game.mechanics;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByWolfListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Wolf && (e.getEntityType().equals(EntityType.WITHER) ||
                e.getEntityType().equals(EntityType.RAVAGER) || e.getEntityType().equals(EntityType.VINDICATOR))) {

            e.setDamage(0);
        }
    }
}
