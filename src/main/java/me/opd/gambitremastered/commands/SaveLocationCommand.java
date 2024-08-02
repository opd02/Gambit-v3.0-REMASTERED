package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveLocationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("savelocation")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location location = player.getLocation();

                GambitRemastered.config.set("Locations." + args[0], location);
                GambitRemastered.instance.saveConfig();
                player.sendMessage(ChatUtil.prefix + ChatUtil.format("Location saved as Locations." + args[0]));
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1f, 1f);

                GambitRemastered.gameSession.getArenaManager().syncConfigLocation();
            }
        }
        return false;
    }
}
