package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.*;
import org.bukkit.block.EndGateway;
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
        Bukkit.getWorlds().getFirst().setGameRule(GameRule.MOB_GRIEFING, false);
        Bukkit.getWorlds().getFirst().setGameRule(GameRule.KEEP_INVENTORY, true);
        Bukkit.getWorlds().getFirst().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
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

        task = Bukkit.getScheduler().runTaskTimer(GambitRemastered.instance, () -> {
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

    public void openEndPortal(TeamType type) {
        PlayerManager.playerSoundForPlayers(Sound.BLOCK_END_PORTAL_FRAME_FILL, 1);

        Location location = ArenaManager.locations.get(type == TeamType.RED ? "RedPortal" : "BluePortal");
        location.getBlock().setType(Material.END_GATEWAY);
        double z = location.getZ();
        double y = location.getY();

        for (double i = z; i >= z - 5; i--) {
            for (double j = y; j <= y + 2; j++) {
                Location portal = new Location(location.getWorld(), location.getX(), j, i);
                portal.getBlock().setType(Material.END_GATEWAY);
                EndGateway gate = (EndGateway) portal.getBlock().getState();
                gate.setExitLocation(type == TeamType.RED ? ArenaManager.locations.get("BlueSpawn") : ArenaManager.locations.get("RedPortal"));
                gate.setExactTeleport(true);
                gate.update();
            }
        }
        //TODO make close portal method
    }
}
