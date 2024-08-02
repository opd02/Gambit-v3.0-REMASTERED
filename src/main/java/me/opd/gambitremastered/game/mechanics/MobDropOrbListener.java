package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobDropOrbListener implements Listener {

    @EventHandler
    public void onMobDie(EntityDeathEvent e) {
        Entity en = e.getEntity();
        Location l = en.getLocation();
        ItemStack item;

        if (e.getEntityType().equals(EntityType.ZOMBIE) || e.getEntityType().equals(EntityType.PILLAGER)) {
            item = ItemUtil.getOrb();
            en.getWorld().dropItemNaturally(l, item);

        } else if (e.getEntityType().equals(EntityType.RAVAGER) || e.getEntityType().equals(EntityType.EVOKER)) {
            item = ItemUtil.getToken();
            en.getWorld().dropItemNaturally(l, item);
        }
    }
}
