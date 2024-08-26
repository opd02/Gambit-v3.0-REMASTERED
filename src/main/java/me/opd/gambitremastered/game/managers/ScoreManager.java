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
                    GambitRemastered.gameSession.getMobManager().setAllowBlueMobSpawning(false);
                    Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&9Blue team &7has spawned the first boss."));
                    PlayerManager.playerSoundForPlayers(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1);
                    GambitRemastered.gameSession.getMobManager().spawnBoss(TeamType.BLUE, 1);
                }
        }
    }

    public void resetAllScores() {
        this.blueScore = 0;
        this.redScore = 0;
        this.blueBossLevel = 0;
        this.redBossLevel = 0;
    }

    //TODO add methods to add and subtract scores - will have to do checks for boss levels
    //TODO make the scores viewable in scoreboard (PAPI?)

}
