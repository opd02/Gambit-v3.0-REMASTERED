package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.managers.ArenaManager;
import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class FreezeMobs extends Prize implements Listener {
    public FreezeMobs(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 10, 1);

        for (Entity en : player.getNearbyEntities(15, 15, 15)) {
            if ((en instanceof LivingEntity) && !(en instanceof Player) && !(en instanceof ArmorStand)) {
                ArenaManager.frozenEntities.add(en);
                LivingEntity le = (LivingEntity) en;
                le.getEquipment().setHelmet(new ItemStack(Material.ICE, 1));
                le.setSilent(true);
                le.setAI(false);
                GambitRemastered.instance.getServer().getScheduler().scheduleSyncDelayedTask(GambitRemastered.instance, () -> {
                    ArenaManager.frozenEntities.remove(en);
                    if (!le.isDead()) {
                        le.getEquipment().setHelmet(new ItemStack(Material.AIR));
                        player.getWorld().playSound(le.getLocation(), Sound.BLOCK_GLASS_PLACE, 1, 1);
                        le.setAI(true);
                        le.setSilent(false);
                    }
                }, 200L);
            }
        }

    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {
        if (ArenaManager.frozenEntities.contains(e.getEntity())) {
            e.setCancelled(true);
        }
    }
}
