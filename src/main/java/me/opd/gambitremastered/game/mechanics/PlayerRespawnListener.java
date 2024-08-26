package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (!GambitRemastered.gameSession.isAllowRespawning()) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(ChatUtil.prefix + ChatUtil.format("&c&lYou have lost the game!"));
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 0.5f);
            e.getPlayer().getInventory().clear();
            return;
        }

        Player p = e.getPlayer();
        PlayerInventory pi = p.getInventory();
        pi.remove(Material.NETHER_STAR);
        p.sendMessage(ChatUtil.prefix + ChatUtil.format("&cYou have died and lost all the orbs you were carrying!"));
        p.setLevel(0);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(GambitRemastered.instance, () -> p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 2)), 2L);
    }
}
