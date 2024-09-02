package me.opd.gambitremastered.util;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class teamBankedPapi extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "gambit";
    }

    @Override
    public @NotNull String getAuthor() {
        return "opd02";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("red_team_banked_amount")) {
            return String.valueOf(GambitRemastered.gameSession.getScoreManager().getRedScore());
        } else if (params.equalsIgnoreCase("blue_team_banked_amount")) {
            return String.valueOf(GambitRemastered.gameSession.getScoreManager().getBlueScore());
        } else if (params.equalsIgnoreCase("next_wave_time")) {
            return String.valueOf(GambitRemastered.gameSession.getMobManager().getTimeUntilNextWave());
        }
        return null;
    }

}
