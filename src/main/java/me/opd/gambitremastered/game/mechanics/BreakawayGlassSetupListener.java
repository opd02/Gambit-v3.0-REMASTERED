package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BreakawayGlassSetupListener implements Listener {

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        if (e.getBlock().getType().equals(Material.YELLOW_STAINED_GLASS)) {
            Location loc = e.getBlock().getLocation();

            //TODO Add the yellow glass that holds players in their chamber until the timer has run out
//            GambitRemastered.config.getList()
            GambitRemastered.instance.saveConfig();

        }
    }
}
