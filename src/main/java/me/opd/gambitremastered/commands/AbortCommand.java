package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.managers.ArenaManager;
import me.opd.gambitremastered.game.managers.PlayerManager;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class AbortCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("abort")) {
            if (commandSender.isOp()) {
                commandSender.sendMessage(ChatUtil.prefix + ChatUtil.format("&cGameplay has been suspended."));
                PlayerManager.playerSoundForPlayers(Sound.ENTITY_VILLAGER_NO, 0.5f);

                GambitRemastered.gameSession.resetGameSession();
                return true;

            } else {
                commandSender.sendMessage(ChatColor.RED + "This command is for operators only.");
                return false;
            }
        }
        return false;
    }
}
