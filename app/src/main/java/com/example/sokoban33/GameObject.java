package com.example.sokoban33;

import android.util.Log;

class GameObject {
    private int posX, posY;
    private int[] level;
    private boolean canMove, canGoLeft, canGoRight, canGoUp, canGoDown, boxIsLeft, boxIsRight, boxIsUp, boxIsDown;

    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
        level = E.actualLevelArray.clone();
        this.canMove = true;
    }

    private static int getCoor(int x, int y) { return y*10+x; }

    private int heroCoor() { return this.posY*10+this.posX; }

    void move(int direction) {

        mapDirections();

        //before moving
        level[heroCoor()] = E.EMPTY;

        //moving
        canMove = true;
        switch(direction){
            case E.RIGHT:
                if(boxIsRight)pushBox(E.RIGHT);
                if(canGoRight&&canMove)this.posX++;
                break;
            case E.LEFT:
                if(boxIsLeft)pushBox(E.LEFT);
                if(canGoLeft&&canMove)this.posX--;
                break;
            case E.UP:
                if(boxIsUp)pushBox(E.UP);
                if(canGoUp&&canMove)this.posY--;
                break;
            case E.DOWN:
                if(boxIsDown)pushBox(E.DOWN);
                if(canGoDown&&canMove)this.posY++;
                break;
        }

        //after moving
        level[heroCoor()] = E.HERO;
        E.actualLevelArray = level;

    }

    private void mapDirections(){

        int heroRight = level[getCoor(this.posX + 1, this.posY)];
        int heroLeft = level[getCoor(this.posX - 1, this.posY)];
        int heroUp = level[getCoor(this.posX, this.posY - 1)];
        int heroDown = level[getCoor(this.posX, this.posY + 1)];

        //if mobile border or wall
        canGoRight = (this.posX < 9) && (heroRight != E.WALL);
        canGoLeft = (this.posX > 0) && (heroLeft!= E.WALL);
        canGoUp = (this.posY > 0) && (heroUp!= E.WALL);
        canGoDown = (this.posY < 9) && (heroDown!= E.WALL);

        //if box or green box
        boxIsLeft = (heroLeft == E.BOX || heroLeft == E.BOXOK);
        boxIsRight = (heroRight == E.BOX || heroRight == E.BOXOK);
        boxIsUp = (heroUp==E.BOX || heroUp==E.BOXOK);
        boxIsDown = (heroDown==E.BOX ||heroDown==E.BOXOK);
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

    public boolean won(){
        boolean won = true;
        for (int value : level) {
            if (value == E.BOX) won = false;
        }
        return won;
    }
}
