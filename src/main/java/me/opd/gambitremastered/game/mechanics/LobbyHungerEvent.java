package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class LobbyHungerEvent implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if (!GambitRemastered.gameSession.getGameState().equals(GameState.IN_GAME)) {
            e.setCancelled(true);
        }
    }
}
