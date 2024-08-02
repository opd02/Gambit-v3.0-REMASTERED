package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.managers.ArenaManager;
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
                GambitRemastered.gameSession.setGameState(GameState.LOBBY);
                Player playerSender = (Player) commandSender;

                for (Entity e : playerSender.getWorld().getEntities()) {
                    if (e.getType() == EntityType.WOLF || e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.PILLAGER || e.getType() == EntityType.RAVAGER || e.getType() == EntityType.WITHER || e.getType() == EntityType.VINDICATOR) {
                        e.remove();
                    }
                }

                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.getInventory().clear();
                    p.getEquipment().clear();
                    p.setLevel(0);
                    p.setHealth(20);
                    p.teleport(ArenaManager.locations.get("LobbySpawn"));
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, (float) 0.1);
                }
                return true;

            } else {
                commandSender.sendMessage(ChatColor.RED + "This command is for operators only.");
                return false;
            }
        }
        return false;
    }
}
