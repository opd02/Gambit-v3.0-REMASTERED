package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {

            GambitRemastered.gameSession.getPlayerManager().teleportPlayersToTeamSpawns();
            GambitRemastered.gameSession.getPlayerManager().giveStartingGear();
            GambitRemastered.gameSession.getMobManager().updateMobSpawnLocations(Bukkit.getWorlds().getFirst());

            GambitRemastered.gameSession.setGameState(GameState.PREGAME);
            GambitRemastered.gameSession.getArenaManager().startPregameCountdown();

        }
        return false;
    }

}
