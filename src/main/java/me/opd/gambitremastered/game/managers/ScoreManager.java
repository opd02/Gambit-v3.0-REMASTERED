package me.opd.gambitremastered.game.managers;

public class ScoreManager {

    private int blueScore;
    private int redScore;
    private int blueBossLevel;
    private int redBossLevel;

    private final int winningScore = 50;

    public ScoreManager() {
        this.blueScore = 0;
        this.blueBossLevel = 0;

        this.redScore = 0;
        this.redBossLevel = 0;
    }

    //TODO add methods to add and subtract scores - will have to do checks for boss levels
    //TODO make the scores viewable in scoreboard (PAPI?)

}
