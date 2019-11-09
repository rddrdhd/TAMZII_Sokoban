package com.example.sokoban33;

import android.util.Log;

public class GameObject {
    public int posX, posY;
    public boolean canGoUp, canGoLeft, canGoRight, canGoDown;

    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public static int getCoor(int x, int y){
        return y*10+x;
    }

    public int heroCoor() {return this.posY*10+this.posX;}

    public void move(int direction) {
        canGoRight = (this.posX<9);
        canGoLeft = (this.posX>0);
        canGoUp = (this.posY>0);
        canGoDown = (this.posY<9);
        //Log.i("HeroPos", heroCoor()+": UP?"+canGoUp+" DOWN?"+canGoDown+" LEFT?"+canGoLeft+" RIGHT?"+canGoRight);

        E.actualLevel[heroCoor()] = E.EMPTY; //sma

        switch(direction){
            case E.RIGHT:
                if(this.canGoRight)this.posX++;
                break;
            case E.LEFT:
                if(this.canGoLeft)this.posX--;
                break;
            case E.UP:
                if(this.canGoUp)this.posY--;
                break;
            case E.DOWN:
                if(this.canGoDown)this.posY++;
                break;
        }

        E.actualLevel[heroCoor()] = E.HERO;

    }
}
