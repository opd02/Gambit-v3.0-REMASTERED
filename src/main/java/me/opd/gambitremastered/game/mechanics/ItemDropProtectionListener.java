package me.opd.gambitremastered.game.mechanics;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropProtectionListener implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {

        Player p = e.getPlayer();

        if (e.getItemDrop().getName().contains("Axe") || e.getItemDrop().getName().contains("Sword") && !(p.getGameMode() == GameMode.CREATIVE)) {
            p.sendMessage(ChatColor.RED + "You can not this drop item in the arena!");
            e.setCancelled(true);
            return;
        }
        if (e.getItemDrop().getItemStack().getType().equals(Material.NETHER_STAR) && !(p.getGameMode() == GameMode.CREATIVE)) {
            p.sendMessage(ChatColor.RED + "You can not this drop item in the arena!");
            e.setCancelled(true);
        }
    }
}
