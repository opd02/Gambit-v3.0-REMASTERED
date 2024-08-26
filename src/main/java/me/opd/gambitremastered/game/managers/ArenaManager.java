package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ArenaManager {

    public static HashMap<String, Location> locations = new HashMap<>();
    public static ArrayList<Entity> frozenEntities = new ArrayList<>();
    private BukkitTask task = null;
    int i;

    public ArenaManager() {
    }

    public void syncConfigLocation() {
        if (GambitRemastered.config.getConfigurationSection("Locations") == null) {
            return;
        }
        for (var loc : GambitRemastered.config.getConfigurationSection("Locations").getKeys(true)) {
            String name = loc.toString().replace("Locations.", "");
            locations.put(name, (Location) GambitRemastered.config.get("Locations." + name));
        }
    }

    public void startPregameCountdown() {
        i = 10;

        task = Bukkit.getScheduler().runTaskTimer(GambitRemastered.instance, (Runnable) () -> {
            if (i != 0) {
                Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatColor.YELLOW + ChatColor.BOLD + "The game will begin in " + i + "...");
                if (i == 1 || i == 2 || i == 3) {
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f);
                }
                i--;
            } else {
                // If "i" is zero, we cancel the task.
                PlayerManager.playerSoundForPlayers(Sound.ENTITY_ENDER_DRAGON_GROWL, 0.75f);
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    GambitRemastered.worldBorderApi.setBorder(player, 500, 10, TimeUnit.SECONDS);
                }
                GambitRemastered.gameSession.setGameState(GameState.IN_GAME);
                Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatColor.DARK_GREEN + "The game has BEGUN!");
                GambitRemastered.gameSession.getMobManager().startMobSpawning();

                task.cancel();
            }
        }, 20, 20);
    }
    //TODO add end portal opening
}
