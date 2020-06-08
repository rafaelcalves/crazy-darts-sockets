package com.uni.redes;

public class Score {
    private int playerIndex;
    private int points;

    Score(int playerIndex){
        this.playerIndex = playerIndex;
    }
    void scorePoints(int points){
        this.points += points;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getPoints() {
        return points;
    }
}
