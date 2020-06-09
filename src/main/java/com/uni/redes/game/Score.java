package com.uni.redes.game;

public class Score {
    private int playerIndex;
    private int points;

    public Score(int playerIndex){
        this.playerIndex = playerIndex;
    }

    public void scorePoints(int points){
        this.points += points;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getPoints() {
        return points;
    }
}
