package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.TeamType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {

            if (GambitRemastered.gameSession.getPlayerManager().getTeamPlayers(TeamType.BLUE).isEmpty() ||
                    GambitRemastered.gameSession.getPlayerManager().getTeamPlayers(TeamType.RED).isEmpty()) {
                sender.sendMessage(ChatColor.RED + "There must be at least one player on each team to begin.");
                return true;
            }

            GambitRemastered.gameSession.getPlayerManager().teleportPlayersToTeamSpawns();
            GambitRemastered.gameSession.getPlayerManager().giveStartingGear();
            GambitRemastered.gameSession.getMobManager().updateMobSpawnLocations(Bukkit.getWorlds().getFirst());

            GambitRemastered.gameSession.setGameState(GameState.PREGAME);
            GambitRemastered.gameSession.getArenaManager().startPregameCountdown();

        }
        return false;
    }

}
