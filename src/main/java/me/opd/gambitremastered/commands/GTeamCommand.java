package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class GTeamCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gteam")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatUtil.format("&cThis command is for players only."));
                return false;
            }

            Player p = (Player) sender;

            if (GambitRemastered.gameSession.getGameState() != GameState.LOBBY) {
                p.sendMessage(ChatUtil.prefix + ChatUtil.format("&cIt is too late to switch teams."));
                return true;
            }

            if (args.length == 0 || !args[0].equalsIgnoreCase("join")) {
                sender.sendMessage(ChatUtil.format("&cUsage: /gteam join <color>"));
                return true;
            }

            String team = args[1].toLowerCase();
            switch (team) {
                case "red" -> GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(p, TeamType.RED);
                case "blue" -> GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(p, TeamType.BLUE);
                case "spectate" ->
                        GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(p, TeamType.SPECTATE);
                default -> sender.sendMessage(ChatUtil.format("&cUsage: /gteam join <color/spectate>"));
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gteam") && args.length == 1) {
            return List.of("join");
        } else if (cmd.getName().equalsIgnoreCase("gteam") && args.length == 2) {
            return List.of("red", "blue", "spectate");
        }
        return null;
    }
}
