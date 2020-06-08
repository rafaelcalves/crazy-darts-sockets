package com.uni.redes;

public class Point {
    private double xPosition;
    private double yPosition;

    Point(float x, float y){
        xPosition = x;
        yPosition = y;
    }

    Point(){
        xPosition = (Math.random() * ((10 - 0) + 1)) + 0;
        yPosition = (Math.random() * ((10 - 0) + 1)) + 0;
    }

    public double getX() {
        return xPosition;
    }

    public double getY() {
        return yPosition;
    }
}
