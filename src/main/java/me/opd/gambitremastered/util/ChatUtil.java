package me.opd.gambitremastered.util;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String permission = format("&cYou do not have permission to access this command.");
    public static String prefix = format("&f&l[&a&lGAMBIT&f&l] &r&7");

}
