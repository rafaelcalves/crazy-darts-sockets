package com.uni.redes;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private int playerIndex;
    private List<Throw> aThrows;
    private Score score;

    Turn(int playerIndex){
        this.playerIndex = playerIndex;
        this.aThrows = new ArrayList<>();
        this.score = new Score(playerIndex);
    }

    void newThrow(){
        Throw aThrow = new Throw();
        aThrow.doThrow();
        aThrows.add(aThrow);
        score.scorePoints(aThrow.getScore());
    }

    public Score getScore() {
        return score;
    }
}
