package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        TeamType deadPlayerTeam = GambitRemastered.gameSession.getPlayerManager().getPlayerTeam(p);
        if (!GambitRemastered.gameSession.isAllowRespawning()) {
            GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(p, TeamType.SPECTATE);
        }

        if (p.getKiller() != null) {
            assert p.getKiller() != null;
            TeamType killerTeam = GambitRemastered.gameSession.getPlayerManager().getPlayerTeam(p.getKiller());

            if (GambitRemastered.gameSession.getPlayerManager().getTeamPlayers(deadPlayerTeam).isEmpty()) {
                GambitRemastered.gameSession.getScoreManager().triggerGameWin(killerTeam);
            }
        }
    }
}
