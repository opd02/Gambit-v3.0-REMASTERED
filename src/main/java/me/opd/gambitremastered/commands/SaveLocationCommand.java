package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.List;

public class SaveLocationCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("savelocation")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location location = player.getLocation();
                if (args[0].equalsIgnoreCase("RedMobSpawnLocation") ||
                        args[0].equalsIgnoreCase("BlueMobSpawnLocation")) {
                    ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
                    armorStand.setVisible(false);
                    armorStand.setCustomName(args[0]);
                    armorStand.setGravity(false);
                    player.sendMessage(ChatUtil.prefix + ChatUtil.format("You have saved a spawn location!"));
                    return true;
                }

                GambitRemastered.config.set("Locations." + args[0], location);
                GambitRemastered.instance.saveConfig();
                player.sendMessage(ChatUtil.prefix + ChatUtil.format("Location saved as Locations." + args[0]));
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1f, 1f);

                GambitRemastered.gameSession.getArenaManager().syncConfigLocation();
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("savelocation")) {
            return List.of("RedMobSpawnLocation", "BlueMobSpawnLocation");
        }
        return null;
    }
}
