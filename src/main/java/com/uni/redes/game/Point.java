package com.uni.redes.game;

public class Point {
    private double xPosition;
    private double yPosition;

    public Point(double x, double y){
        xPosition = x;
        yPosition = y;
    }

    public Point(){
        xPosition = (Math.random() * 11);
        yPosition = (Math.random() * 11);
    }

    public double getX() {
        return xPosition;
    }

    public double getY() {
        return yPosition;
    }
}
