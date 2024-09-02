package me.opd.gambitremastered.commands.gteamGUI;

import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        int slot = event.getSlot();
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("Gambit")) {
            Menu menu = (Menu) player.getMetadata("Gambit").get(0).value();

            for (Button buttons : menu.getButtons()) {
                if (buttons.getSlot() == slot) {
                    buttons.onClick(player);
                }
            }
            player.closeInventory();
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("Gambit")) {
            player.removeMetadata("Gambit", GambitRemastered.instance);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().getType().equals(Material.COMPASS)) {
            event.setCancelled(true);
            new GTeamMenu().displayTo(player);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2f);
//            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2, 2f);
        }
    }
}