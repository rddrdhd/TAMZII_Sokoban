package com.example.sokoban33;

public class GameObject {
    public int posX;
    public int posY;
    //public final int[] LEVEL;

    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
        //this.LEVEL = E.LEVELS[0];
    }
    public static int getCoor(int x, int y){
        return y*10+x;
    }
    public int heroCoor() {return this.posY*10+this.posX;}
    public void moveRight(){

        E.actualLevel[heroCoor()] = 0;
        this.posX++;
        E.actualLevel[heroCoor()] = 4;

    }
}
