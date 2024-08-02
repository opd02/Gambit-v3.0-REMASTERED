package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.managers.ArenaManager;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");

        if (GambitRemastered.gameSession.getGameState().equals(GameState.LOBBY)) {
            player.teleport(ArenaManager.locations.get("LobbySpawn"));

            player.sendMessage(ChatUtil.prefix + ChatUtil.format("Welcome to Gambit! Type &r/gteam join <blue/red>&7 to join a team!"));
            player.getInventory().clear();
            player.getEquipment().clear();
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setSaturation(20);

            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1f, 1f);
        }

    }
}
