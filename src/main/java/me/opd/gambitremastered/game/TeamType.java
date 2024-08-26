package me.opd.gambitremastered.game;

import me.opd.gambitremastered.GambitRemastered;

import java.util.UUID;

public enum TeamType {
    BLUE,
    RED,
    SPECTATE;

    public static TeamType getTeam(UUID uuid) {
        if (!GambitRemastered.gameSession.getPlayerManager().getPlayers().containsKey(uuid)) {
            GambitRemastered.gameSession.getPlayerManager().getPlayers().put(uuid, TeamType.SPECTATE);
        }
        return GambitRemastered.gameSession.getPlayerManager().getPlayers().get(uuid);
    }
}
