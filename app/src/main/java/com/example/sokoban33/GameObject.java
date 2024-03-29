package com.example.sokoban33;

import android.util.Log;

class GameObject {
    public int posX, posY;
    private boolean canMove, canGoLeft, canGoRight, canGoUp, canGoDown, boxIsLeft, boxIsRight, boxIsUp, boxIsDown;

    GameObject(int x, int y){
        this.posX = x;
        this.posY = y;
        this.canMove = true;
    }

    private static int getCoor(int x, int y) { return y*10+x; }

    private int heroCoor() { return this.posY*10+this.posX; }

    void move(int direction) {

        if (E.resetingLevel){
            this.posX = E.startX;
            this.posY = E.startY;
            E.touches = 0;
            E.resetingLevel = false;
        }

        mapDirections();

        //before moving
        E.actualLevelArray[heroCoor()] = E.EMPTY;

        //moving hero
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

        //after moving set hero on his place
        E.actualLevelArray[heroCoor()] = E.HERO;

    }

    private void mapDirections(){

        int heroRight = E.actualLevelArray[getCoor(this.posX+1, this.posY)];
        int heroLeft = E.actualLevelArray[getCoor(this.posX-1, this.posY)];
        int heroUp = E.actualLevelArray[getCoor(this.posX, this.posY-1)];
        int heroDown = E.actualLevelArray[getCoor(this.posX, this.posY+1)];

        //if View border or wall
        canGoRight = (this.posX<9) && (heroRight!=E.WALL);
        canGoLeft = (this.posX>0) && (heroLeft!=E.WALL);
        canGoUp = (this.posY>0) && (heroUp!=E.WALL);
        canGoDown = (this.posY<9) && (heroDown!=E.WALL);

        //if box or green box
        boxIsLeft = (heroLeft==E.BOX || heroLeft==E.BOXOK);
        boxIsRight = (heroRight==E.BOX || heroRight==E.BOXOK);
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

        //helper variable for potentional box coordinations
        int place = E.actualLevelArray[newCoor];

        if( place!=E.BOX && place!=E.BOXOK && place!=E.WALL) {
            newBoxCoor = newCoor;
        } else {
            newBoxCoor = oldBoxCoor;
            canMove = false;
        }
        //move box in array
        E.actualLevelArray[oldBoxCoor] = E.EMPTY;
        E.actualLevelArray[newBoxCoor] = E.BOX;
    }

    public boolean won(){
        boolean won = true;
        for (int value : E.actualLevelArray) {
            if (value == E.BOX) won = false;
        }
        return won;
    }
}
