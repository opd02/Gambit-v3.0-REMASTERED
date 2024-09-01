package me.opd.gambitremastered.game;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.managers.ArenaManager;
import me.opd.gambitremastered.game.managers.MobManager;
import me.opd.gambitremastered.game.managers.PlayerManager;
import me.opd.gambitremastered.game.managers.ScoreManager;

public class GameSession {

    private GameState gameState;
    private PlayerManager playerManager;
    private ScoreManager scoreManager;
    private MobManager mobManager;
    private ArenaManager arenaManager;
    private boolean allowRespawning;

    public GameSession() {
        this.setGameState(GameState.LOBBY);
        this.playerManager = new PlayerManager();
        this.scoreManager = new ScoreManager();
        this.mobManager = new MobManager();
        this.arenaManager = new ArenaManager();
        this.allowRespawning = true;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean isAllowRespawning() {
        return allowRespawning;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public MobManager getMobManager() {
        return mobManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public void setAllowRespawning(boolean allowRespawning) {

        this.allowRespawning = allowRespawning;
    }

    public void resetGameSession(){
        arenaManager.closeEndPortals();
        playerManager.clearAllTeams();
        playerManager.teleportPlayersToLobby();
        mobManager.clearMobSpawnLocations();
        mobManager.clearAllArenaMobs();
        scoreManager.resetAllScores();
        this.gameState = GameState.LOBBY;
    }
}
