package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ScoreManager {

    private int blueScore;
    private int redScore;
    private int blueBossLevel;
    private int redBossLevel;
    //0 first wave spawning
    //1 first boss is out
    //2 second wave spawning
    //3 second boss out
    //4 third wave spawning
    //5 wither fight
    //6 dead wither, no spawning, portal open
    private int firstBossAmount = 15;
    private int secondBossAmount = 35;
    private final int winningScore = 50;

    //TODO Add a check for winning and make winning animation/map reset

    public ScoreManager() {
        this.blueScore = 0;
        this.blueBossLevel = 0;

        this.redScore = 0;
        this.redBossLevel = 0;
    }

    public void tryToAddPoints(TeamType team, int points, Player p) {
        if (team == TeamType.BLUE) {
            if (blueBossLevel == 1 || blueBossLevel == 3 || blueBossLevel == 5) {
                p.sendMessage(ChatUtil.prefix + ChatUtil.format("&cYou cannot bank orbs while a boss is out!"));
                return;
            }
            //START
            blueScore = blueScore + points;

            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            p.getInventory().remove(Material.NETHER_STAR);
            p.sendMessage(ChatUtil.prefix + ChatUtil.format("&aYou have successfully banked &e&l" + p.getLevel() + " orbs&r&a!"));
            p.sendTitle(ChatUtil.format("&b&l" + blueScore + " &9orbs"), ChatUtil.format("&fnow in your team bank"));
            int spingive = points / 5;
            p.setLevel(0);

            if (spingive > 0) {
                p.sendMessage(ChatUtil.prefix + ChatUtil.format("&eYou received &b" + spingive + "&e spin token(s)!"));
                p.getInventory().addItem(ItemUtil.getToken(spingive));
            }
            checkForBossSpawn(TeamType.BLUE);
            //END
        } else {
            if (redBossLevel == 1 || redBossLevel == 3 || redBossLevel == 5) {
                p.sendMessage(ChatUtil.prefix + ChatUtil.format("&cYou cannot bank orbs while a boss is out!"));
                return;
            }
            redScore = redScore + points;

            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            p.getInventory().remove(Material.NETHER_STAR);
            p.sendMessage(ChatUtil.prefix + ChatUtil.format("&aYou have successfully banked &e&l" + p.getLevel() + " orbs&r&a!"));
            p.sendTitle(ChatUtil.format("&6&l" + redScore + " &corbs"), ChatUtil.format("&fnow in your team bank"));
            int spingive = points / 5;
            p.setLevel(0);

            if (spingive > 0) {
                p.sendMessage(ChatUtil.prefix + ChatUtil.format("&eYou received &b" + spingive + "&e spin token(s)!"));
                p.getInventory().addItem(ItemUtil.getToken(spingive));
            }
            checkForBossSpawn(TeamType.RED);
        }
    }

    private void checkForBossSpawn(TeamType team) {
        switch (team) {
            case BLUE:
                if (blueScore >= firstBossAmount && blueBossLevel < 1) {
                    increaseBossLevel(TeamType.BLUE);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&9Blue team &7has spawned the first boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.BLUE, 1);
                } else if (blueScore >= secondBossAmount && blueBossLevel == 2) {
                    increaseBossLevel(TeamType.BLUE);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&9Blue team &7has spawned the second boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.BLUE, 2);
                } else if (blueScore >= winningScore && blueBossLevel == 4) {
                    increaseBossLevel(TeamType.BLUE);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&9Blue team &7has spawned the final boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.BLUE, 3);
                }
            case RED:
                if (redScore >= firstBossAmount && redBossLevel < 1) {
                    increaseBossLevel(TeamType.RED);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&cRed team &7has spawned the first boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.RED, 1);
                } else if (redScore >= secondBossAmount && redBossLevel == 2) {
                    increaseBossLevel(TeamType.RED);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&cRed team &7has spawned the second boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.RED, 2);
                } else if (redScore >= winningScore && redBossLevel == 4) {
                    increaseBossLevel(TeamType.RED);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&cRed team &7has spawned the final boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.RED, 3);
                }
        }
    }

    public void increaseBossLevel(TeamType team) {
        if (team.equals(TeamType.RED)) {
            redBossLevel = redBossLevel + 1;
            if (redBossLevel == 1 || redBossLevel == 3 || redBossLevel == 5) {
                GambitRemastered.gameSession.getMobManager().setAllowRedMobSpawning(false);
            } else {
                GambitRemastered.gameSession.getMobManager().setAllowRedMobSpawning(true);
            }
        } else {
            blueBossLevel = blueBossLevel + 1;
            if (blueBossLevel == 1 || blueBossLevel == 3 || blueBossLevel == 5) {
                GambitRemastered.gameSession.getMobManager().setAllowBlueMobSpawning(false);
            } else {
                GambitRemastered.gameSession.getMobManager().setAllowBlueMobSpawning(true);
            }
        }
    }

    public void resetAllScores() {
        this.blueScore = 0;
        this.redScore = 0;
        this.blueBossLevel = 0;
        this.redBossLevel = 0;
    }
    //TODO make the scores viewable in scoreboard (PAPI?)

    //TODO Make winning animation/more grand

}
