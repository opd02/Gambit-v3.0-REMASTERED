package me.opd.gambitremastered.game;

import me.opd.gambitremastered.GambitRemastered;

import java.util.UUID;

public enum TeamType {
    BLUE,
    RED,
    SPECTATE;

    public static TeamType getTeam(UUID uuid) {
        return GambitRemastered.gameSession.getPlayerManager().getPlayers().get(uuid);
    }
}
