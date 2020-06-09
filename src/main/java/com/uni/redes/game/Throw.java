package com.uni.redes.game;

public class Throw {
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = 0;

    private Point point;

    public void doThrow(){
        point = new Point(); //TODO: how to throw
    }

    public int getScore(){
        int score = MAX_SCORE - calculateDistanceFromCenter();
        return score > MIN_SCORE ? score : MIN_SCORE;
    }

    public int calculateDistanceFromCenter(){
        return (int)Math.sqrt(Math.pow(point.getX(),2) + Math.pow(point.getY(),2));
    }
}
