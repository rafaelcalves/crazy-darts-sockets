package com.uni.redes;

public class Player {
    protected int id;
    protected Score score;
    Player(){
        score = new Score(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Score getScore() {
        return score;
    }
}
