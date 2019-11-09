package com.example.sokoban33;

import android.util.Log;
import android.widget.Toast;

public class Hero {
    public int posX;
    public int posY;
    private int position;
    public boolean canMoveUp;
    public boolean canMoveDown;
    public boolean canMoveLeft;
    public boolean canMoveRight;
    private int[] level;


    Hero(int[] level){
        new Hero(6, 4, level);
    }

    Hero(int x, int y, int[] level){
        this.level = level;
        this.setPosition(x, y);
        this.canMoveDown = true;
        this.canMoveLeft = true;
        this.canMoveRight = true;
        this.canMoveUp = true;

        Log.i("hero", "Hero created("+this.getPosition()+")");
    }

    public int getPosition(int x, int y){
        return y*10+x;
    }

    public int getPosition(){
        return this.position;
    }

    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
        this.position = this.getPosition(x,y);
        this.level[this.position] = E.HERO;
        Log.i("hero", "Hero position set ("+this.getPosition()+")");
    }

    public void moveRight(){
        if(this.canMoveRight)this.setPosition(++this.posX, this.posY);
        Log.i("hero", "moving right ("+this.getPosition()+")");
    }

    public void moveLeft(){
        if(this.canMoveLeft)this.setPosition(--this.posX, this.posY);
        Log.i("hero", "moving left("+this.getPosition()+")");
    }

    public void moveUp(){
        if(this.canMoveUp)this.setPosition(this.posX, --this.posY);
        Log.i("hero", "moving up("+this.getPosition()+")");
    }

    public void moveDown(){
        if(this.canMoveDown)this.setPosition(this.posX, ++this.posY);
        Log.i("hero", "moving down("+this.getPosition()+")");
    }

    private void setMovementOptions(int[] level){

        if ( level[this.getPosition()-1] == 1
                || this.posX==1
                || (this.getPosition()-1 == 1 & isPushigBox(level, "left"))) {
            this.canMoveLeft = false;
        }
        Log.i("hero", "Hero.canMoveLeft="+canMoveLeft);

        if ( level[this.getPosition()+1] == 1
                || this.posX==9
                || (getPosition()+1 == 9 & isPushigBox(level, "right"))) {
            this.canMoveRight = false;
        }
        Log.i("hero", "Hero.canMoveRight="+canMoveRight);

        if ( level[this.getPosition(this.posX, this.posY-1)] == 1
                || this.posY==1) {
            this.canMoveUp = false;}
        Log.i("hero", "Hero.canMoveUp="+canMoveUp);

        if ( level[this.getPosition(this.posX, this.posY+1)] == 1
                || this.posY==9) {
            this.canMoveDown = false;
        }
        Log.i("hero", "Hero.canMoveDown="+canMoveDown);

    }
    private boolean isPushigBox(int[] level, String direction){
        Log.i("hero", "Hero pushing box:"+direction);
        switch(direction){
            case E.LEFT:
                return level[this.getPosition() - 1] == 2;
            case E.RIGHT:
                return level[this.getPosition() + 1] == 2;
            case E.UP:
                return level[this.getPosition(this.posX, this.posY - 1)] == 2;
            case E.DOWN:
                return level[this.getPosition(this.posX, this.posY + 1)] == 2;
            default:
                return false;
        }
    }


}

