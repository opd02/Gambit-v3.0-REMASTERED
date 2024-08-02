package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {

            GambitRemastered.gameSession.getPlayerManager().teleportPlayersToTeamSpawns();
            GambitRemastered.gameSession.getPlayerManager().giveStartingGear();

            GambitRemastered.gameSession.setGameState(GameState.PREGAME);

        }
        return false;
    }

}
