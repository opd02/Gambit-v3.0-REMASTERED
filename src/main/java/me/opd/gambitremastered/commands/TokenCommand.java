package me.opd.gambitremastered.commands;

import me.opd.gambitremastered.util.ChatUtil;
import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("token")) {
            if (!sender.isOp() || !(sender instanceof Player)) {
                sender.sendMessage(ChatUtil.permission);
                return true;
            }

            Player player = (Player) sender;
            int amount = 1;
            if (args.length != 0) {
                amount = Integer.parseInt(args[0]);
            }
            player.getInventory().addItem(ItemUtil.getToken(amount));
        }
        return false;
    }
}
