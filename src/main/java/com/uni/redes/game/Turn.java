package com.uni.redes.game;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private int playerIndex;
    private List<Throw> aThrows;
    private Score score;

    public Turn(int playerIndex){
        this.playerIndex = playerIndex;
        this.aThrows = new ArrayList<>();
        this.score = new Score(playerIndex);
    }

    public void newThrow(){
        Throw aThrow = new Throw();
        aThrow.doThrow();
        aThrows.add(aThrow);
        score.scorePoints(aThrow.getScore());
    }

    public Score getScore() {
        return score;
    }
}
