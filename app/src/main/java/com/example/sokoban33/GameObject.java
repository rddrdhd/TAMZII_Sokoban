package com.example.sokoban33;

import android.util.Log;

public class GameObject {
    public int posX, posY;
    public boolean canGoUp, canGoLeft, canGoRight, canGoDown;
    public boolean boxIsLeft, boxIsRight, boxIsUp, boxIsDown;
    private int[] level;

    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
        level = E.actualLevelArray.clone();
    }

    private static int getCoor(int x, int y){
        return y*10+x;
    }

    private int heroCoor() {return this.posY*10+this.posX;}

    void move(int direction) {
        canGoRight = (this.posX<9) && (level[getCoor(this.posX+1, this.posY)]!=E.WALL);
        canGoLeft = (this.posX>0)&& (level[getCoor(this.posX-1, this.posY)]!=E.WALL);
        canGoUp = (this.posY>0)&& (level[getCoor(this.posX, this.posY-1)]!=E.WALL);
        canGoDown = (this.posY<9)&& (level[getCoor(this.posX, this.posY+1)]!=E.WALL);

        boxIsLeft = (level[getCoor(this.posX-1, this.posY)]==E.BOX ||level[getCoor(this.posX-1, this.posY)]==E.BOXOK);
        boxIsRight = (level[getCoor(this.posX+1, this.posY)]==E.BOX || level[getCoor(this.posX+1, this.posY)]==E.BOXOK);
        boxIsUp = (level[getCoor(this.posX, this.posY-1)]==E.BOX || level[getCoor(this.posX, this.posY-1)]==E.BOXOK);
        boxIsDown = (level[getCoor(this.posX, this.posY+1)]==E.BOX ||level[getCoor(this.posX, this.posY+1)]==E.BOXOK);
        //Log.i("HeroPos", heroCoor()+": UP?"+canGoUp+" DOWN?"+canGoDown+" LEFT?"+canGoLeft+" RIGHT?"+canGoRight);

        level[heroCoor()] = E.EMPTY; //erase hero

        switch(direction){
            case E.RIGHT:
                if(boxIsRight)pushBox(E.RIGHT);
                if(this.canGoRight)this.posX++;
                break;
            case E.LEFT:
                if(boxIsLeft)pushBox(E.LEFT);
                if(this.canGoLeft)this.posX--;
                break;
            case E.UP:
                if(boxIsUp)pushBox(E.UP);
                if(this.canGoUp)this.posY--;
                break;
            case E.DOWN:
                if(boxIsDown)pushBox(E.DOWN);
                if(this.canGoDown)this.posY++;
                break;
        }

        level[heroCoor()] = E.HERO; //write hero
        E.actualLevelArray = level;

    }

    private void pushBox(int direction){
        int oldBoxCoor ;
        int newBoxCoor;
        switch (direction){
            case E.RIGHT:
                oldBoxCoor = getCoor(this.posX+1, this.posY);
                newBoxCoor = getCoor(this.posX+2, this.posY);
                break;
            case E.LEFT:
                oldBoxCoor = getCoor(this.posX-1, this.posY);
                newBoxCoor = getCoor(this.posX-2, this.posY);
                break;
            case E.UP:
                oldBoxCoor = getCoor(this.posX, this.posY-1);
                newBoxCoor = getCoor(this.posX, this.posY-2);
                break;
            case E.DOWN:
                oldBoxCoor = getCoor(this.posX, this.posY+1);
                newBoxCoor = getCoor(this.posX, this.posY+2);
                break;
            default:
                oldBoxCoor = heroCoor();
                newBoxCoor = oldBoxCoor;
        }

        level[oldBoxCoor] = E.EMPTY;
        level[newBoxCoor] = E.BOX;
    }
}
