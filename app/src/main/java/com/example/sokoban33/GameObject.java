package com.example.sokoban33;

import android.util.Log;

import java.util.Arrays;

public class GameObject {
    public int posX, posY;
    public boolean canGoUp, canGoLeft, canGoRight, canGoDown;
    public boolean boxIsLeft, boxIsRight, boxIsUp, boxIsDown;
    private int[] level;
    private boolean canMove;

    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
        level = E.actualLevelArray.clone();
        this.canMove = true;
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

        canMove = true;
        switch(direction){
            case E.RIGHT:
                if(boxIsRight)pushBox(E.RIGHT);
                if(this.canGoRight&&canMove)this.posX++;
                break;
            case E.LEFT:
                if(boxIsLeft)pushBox(E.LEFT);
                if(this.canGoLeft&&canMove)this.posX--;
                break;
            case E.UP:
                if(boxIsUp)pushBox(E.UP);
                if(this.canGoUp&&canMove)this.posY--;
                break;
            case E.DOWN:
                if(boxIsDown)pushBox(E.DOWN);
                if(this.canGoDown&&canMove)this.posY++;
                break;
        }
        if(won()){
            Log.i("push","You WON");
        }
        level[heroCoor()] = E.HERO; //write hero
        E.actualLevelArray = level;


    }

    private void pushBox(int direction){
        int oldBoxCoor;
        int newCoor;
        int newBoxCoor;

        switch (direction){
            case E.RIGHT:
                oldBoxCoor = getCoor(this.posX+1, this.posY);
                newCoor = getCoor(this.posX+2, this.posY);
                break;
            case E.LEFT:
                oldBoxCoor = getCoor(this.posX-1, this.posY);
                newCoor = getCoor(this.posX-2, this.posY);
                break;
            case E.UP:
                oldBoxCoor = getCoor(this.posX, this.posY-1);
                newCoor = getCoor(this.posX, this.posY-2);
                break;
            case E.DOWN:
                oldBoxCoor = getCoor(this.posX, this.posY+1);
                newCoor = getCoor(this.posX, this.posY+2);
                break;
            default:
                oldBoxCoor = heroCoor();
                newCoor = oldBoxCoor;
        }

        if(level[newCoor]!=E.BOX && level[newCoor]!=E.BOXOK && level[newCoor]!=E.WALL){
            newBoxCoor = newCoor;
        } else {
            newBoxCoor = oldBoxCoor;
            canMove = false;
        }
        level[oldBoxCoor] = E.EMPTY;
        level[newBoxCoor] = E.BOX;
    }

    public boolean won(){ //TODO: vrátí true až po dalším kliknutí
        boolean won = true;
        for(int i = 0; i< level.length; i++){
            if(level[i]==E.BOX) won = false;
        }
        return won;
    }
}
