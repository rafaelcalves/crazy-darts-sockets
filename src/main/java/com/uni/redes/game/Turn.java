package com.uni.redes.game;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    public static final int MAX_THROWS = 3;
    private int playerIndex;
    private List<Throw> aThrows;
    private Score score;

    public Turn(int playerIndex) {
        this.playerIndex = playerIndex;
        this.aThrows = new ArrayList<>();
        this.score = new Score(playerIndex);
    }

    public int newThrow(double x, double y) {
        if (hasFinished()) return -1;

        Throw aThrow = new Throw(x, y);
        aThrows.add(aThrow);
        score.scorePoints(aThrow.getScore());
        return aThrow.getScore();
    }

    public Score getScore() {
        return score;
    }

    public int getPlayerIndex() {
        return this.playerIndex;
    }

    public boolean hasFinished(){
        return aThrows.size() == MAX_THROWS;
    }
}
