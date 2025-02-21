package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.GameState;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Random;

public class MobManager {
    private ArrayList<Location> redMobSpawnLocations;
    private ArrayList<Location> blueMobSpawnLocations;
    private boolean allowRedMobSpawning = true;
    private boolean allowBlueMobSpawning = true;
    private BukkitTask stask = null;
    private BukkitTask timerTask = null;
    private int high = 200;
    public int timeUntilNextWave;

    public MobManager() {
        redMobSpawnLocations = new ArrayList<>();
        blueMobSpawnLocations = new ArrayList<>();
        this.timeUntilNextWave = 0;
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
        this.timeUntilNextWave = 46;
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

        timerTask = Bukkit.getScheduler().runTaskTimer(GambitRemastered.instance, () -> {
            if (!GambitRemastered.gameSession.getGameState().equals(GameState.IN_GAME)) {
                timerTask.cancel();
            }
            if (timeUntilNextWave > 1) {
                this.timeUntilNextWave = timeUntilNextWave - 1;
            } else {
                this.timeUntilNextWave = 45;
            }
        }, 0, 20);
    }

    private void spawnMobWave(TeamType teamType) {
        Random random = new Random();
        if (teamType == TeamType.RED) {
            Location redSpawnLocation = redMobSpawnLocations.get(random.nextInt(redMobSpawnLocations.size()));
            spawnWave(redSpawnLocation);

        } else {
            Location blueSpawnLocation = blueMobSpawnLocations.get(random.nextInt(blueMobSpawnLocations.size()));
            spawnWave(blueSpawnLocation);
        }
    }

    public void spawnWave(Location location) {
        for (int i = 1; i <= 5; i++) {
            Zombie zombie = location.getWorld().spawn(location, Zombie.class);
            zombie.setAdult();
            zombie.setCanPickupItems(false);
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 7 * 20, 1));
        }

        location.getWorld().spawn(location, Pillager.class);
    }

    public void spawnBoss(TeamType teamType, int bossLevel) {
        Random random = new Random();
        Location location = ((teamType == TeamType.RED) ? redMobSpawnLocations.get(random.nextInt(redMobSpawnLocations.size())) :
                blueMobSpawnLocations.get(random.nextInt(blueMobSpawnLocations.size())));
        switch (bossLevel) {
            case 1 -> {
                Vindicator vindicator = location.getWorld().spawn(location, Vindicator.class);
                vindicator.setCustomName(ChatUtil.format("&dBOSS"));
                vindicator.setCustomNameVisible(true);
                vindicator.setMaxHealth(200);
                vindicator.setHealth(200);
                vindicator.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                vindicator.setRemoveWhenFarAway(false);
            }
            case 2 -> {
                Ravager ravager = location.getWorld().spawn(location, Ravager.class);
                ravager.setCustomName(ChatUtil.format("&dBOSS"));
                ravager.setCustomNameVisible(true);
                ravager.setMaxHealth(300);
                ravager.setHealth(300);
                ravager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                ravager.setRemoveWhenFarAway(false);
            }
            case 3 -> {
                Wither wither = location.getWorld().spawn(location, Wither.class);
                wither.setCustomName(ChatUtil.format("&dFINAL BOSS"));
                wither.setCustomNameVisible(true);
                wither.setMaxHealth(450);
                wither.setHealth(450);
                wither.setRemoveWhenFarAway(false);
            }
            default -> {
            }
        }
    }

    public void setAllowRedMobSpawning(boolean allowRedMobSpawning) {
        this.allowRedMobSpawning = allowRedMobSpawning;
    }

    public void setAllowBlueMobSpawning(boolean allowBlueMobSpawning) {
        this.allowBlueMobSpawning = allowBlueMobSpawning;
    }

    public void clearAllArenaMobs() {
        for (Entity e : Bukkit.getServer().getWorlds().getFirst().getEntities()) {
            if (e.getType() == EntityType.WOLF || e.getType() == EntityType.ZOMBIE ||
                    e.getType() == EntityType.PILLAGER || e.getType() == EntityType.RAVAGER ||
                    e.getType() == EntityType.WITHER || e.getType() == EntityType.VINDICATOR ||
                    e.getType() == EntityType.ARROW || e.getType() == EntityType.ITEM) {
                e.remove();
            }
        }
    }

    public ArrayList<Location> getAllSpawnLocations() {
        ArrayList<Location> spawnLocations = new ArrayList<>();
        spawnLocations.addAll(this.blueMobSpawnLocations);
        spawnLocations.addAll(this.redMobSpawnLocations);
        return spawnLocations;
    }

    public int getTimeUntilNextWave() {
        return timeUntilNextWave;
    }
}
