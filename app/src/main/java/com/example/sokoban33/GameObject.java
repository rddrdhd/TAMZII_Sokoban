package com.example.sokoban33;

import android.util.Log;

public class GameObject {
    public int posX, posY;
    public boolean canGoUp, canGoLeft, canGoRight, canGoDown;
    private int[] level;




    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
        level = E.actualLevelArray.clone();
    }

    public static int getCoor(int x, int y){
        return y*10+x;
    }

    public int heroCoor() {return this.posY*10+this.posX;}

    public void move(int direction) {
        canGoRight = (this.posX<9) && (level[getCoor(this.posX+1, this.posY)]!=E.WALL);
        canGoLeft = (this.posX>0)&& (level[getCoor(this.posX-1, this.posY)]!=E.WALL);
        canGoUp = (this.posY>0)&& (level[getCoor(this.posX, this.posY-1)]!=E.WALL);
        canGoDown = (this.posY<9)&& (level[getCoor(this.posX, this.posY+1)]!=E.WALL);
        //Log.i("HeroPos", heroCoor()+": UP?"+canGoUp+" DOWN?"+canGoDown+" LEFT?"+canGoLeft+" RIGHT?"+canGoRight);

        level[heroCoor()] = E.EMPTY; //sma

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

        level[heroCoor()] = E.HERO;

        E.actualLevelArray = level;

    }
}
