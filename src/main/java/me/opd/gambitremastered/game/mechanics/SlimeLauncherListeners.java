package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SlimeLauncherListeners implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Block block = event.getTo().getBlock().getRelative(BlockFace.DOWN);

        if (block.getType().equals(Material.SLIME_BLOCK)) {
            player.setVelocity(player.getLocation().getDirection().multiply(2.5).setY(2));
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 10, 10);
            if (!GambitRemastered.gameSession.getPlayerManager().getSlimeLaunching().contains(player.getUniqueId())) {
                GambitRemastered.gameSession.getPlayerManager().getSlimeLaunching().add(player.getUniqueId());
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(GambitRemastered.instance, () -> {
                if (GambitRemastered.gameSession.getPlayerManager().getSlimeLaunching().
                        contains(player.getUniqueId())) {
                    event.setCancelled(true);
                    GambitRemastered.gameSession.getPlayerManager().getSlimeLaunching().remove(player.getUniqueId());
                }
            }, 20 * 7L);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (GambitRemastered.gameSession.getPlayerManager().getSlimeLaunching().
                    contains(event.getEntity().getUniqueId())) {
                event.setCancelled(true);
                GambitRemastered.gameSession.getPlayerManager().getSlimeLaunching().remove(event.getEntity().getUniqueId());
            }
        }
    }

}
