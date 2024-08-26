package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.TeamType;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Random;

public class MobManager {
    //TODO add the wave spawning mechanics that check for GameState and boss levels active etc.
    private ArrayList<Location> redMobSpawnLocations;
    private ArrayList<Location> blueMobSpawnLocations;
    private boolean allowRedMobSpawning = true;
    private boolean allowBlueMobSpawning = true;
    private BukkitTask stask = null;
    private int high = 200;

    public MobManager() {
        redMobSpawnLocations = new ArrayList<>();
        blueMobSpawnLocations = new ArrayList<>();
    }

    public void updateMobSpawnLocations(World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand && entity.getCustomName() != null) {
                if (entity.getCustomName().equalsIgnoreCase("RedMobSpawnLocation")) {
                    redMobSpawnLocations.add(entity.getLocation());
                } else if (entity.getCustomName().equalsIgnoreCase("BlueMobSpawnLocation")) {
                    blueMobSpawnLocations.add(entity.getLocation());
                }
            }
        }
        System.out.println(redMobSpawnLocations.size());
        System.out.println(blueMobSpawnLocations.size());
    }

    public void clearMobSpawnLocations() {
        redMobSpawnLocations.clear();
        blueMobSpawnLocations.clear();
    }

    public void startMobSpawning() {
        stask = Bukkit.getScheduler().runTaskTimer(GambitRemastered.instance, () -> {
            if (high != 0) {
                if (!GambitRemastered.gameSession.getGameState().equals(GameState.IN_GAME)) {
                    stask.cancel();
                    return;
                }
                PlayerManager.playerSoundForPlayers(Sound.BLOCK_TRIPWIRE_CLICK_ON, 1);
                if (allowRedMobSpawning)
                    spawnMobWave(TeamType.RED);

                if (allowBlueMobSpawning)
                    spawnMobWave(TeamType.BLUE);

                high--;
            } else {
                // If "i" is zero, we cancel the task.
                Bukkit.broadcastMessage(ChatColor.BOLD + "SERVER RAN OUT OF SPAWNING REPEATS");
                stask.cancel();
            }
        }, 0, 900L);
    }

    private void spawnMobWave(TeamType teamType) {
        Random random = new Random();
        if (teamType == TeamType.RED) {
            Location redSpawnLocation = redMobSpawnLocations.get(random.nextInt(redMobSpawnLocations.size()));
            redSpawnLocation.getWorld().spawn(redSpawnLocation, Zombie.class);
            redSpawnLocation.getWorld().spawn(redSpawnLocation, Zombie.class);
            redSpawnLocation.getWorld().spawn(redSpawnLocation, Zombie.class);
            redSpawnLocation.getWorld().spawn(redSpawnLocation, Zombie.class);
            redSpawnLocation.getWorld().spawn(redSpawnLocation, Zombie.class);
            redSpawnLocation.getWorld().spawn(redSpawnLocation, Pillager.class);

        } else {
            Location blueSpawnLocation = blueMobSpawnLocations.get(random.nextInt(blueMobSpawnLocations.size()));
            blueSpawnLocation.getWorld().spawn(blueSpawnLocation, Zombie.class);
            blueSpawnLocation.getWorld().spawn(blueSpawnLocation, Zombie.class);
            blueSpawnLocation.getWorld().spawn(blueSpawnLocation, Zombie.class);
            blueSpawnLocation.getWorld().spawn(blueSpawnLocation, Zombie.class);
            blueSpawnLocation.getWorld().spawn(blueSpawnLocation, Zombie.class);
            blueSpawnLocation.getWorld().spawn(blueSpawnLocation, Pillager.class);

        }
    }

    public void spawnBoss(TeamType teamType, int bossLevel) {
        //TODO implement
    }

    public void setAllowRedMobSpawning(boolean allowRedMobSpawning) {
        this.allowRedMobSpawning = allowRedMobSpawning;
    }

    public void setAllowBlueMobSpawning(boolean allowBlueMobSpawning) {
        this.allowBlueMobSpawning = allowBlueMobSpawning;
    }
}
