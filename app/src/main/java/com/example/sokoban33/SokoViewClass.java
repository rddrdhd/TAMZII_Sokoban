package com.example.sokoban33;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by kru13 on 12.10.16.
 */

public class SokoViewClass extends View{

    Bitmap[] bmp;


    int lx = 10;
    int ly = 10;

    int width;
    int height;
    Hero hero;


    private int level[] = {//nacitat levely odjinud
            1,1,1,1,1,1,1,1,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,4,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0
    };




    public SokoViewClass(Context context) {
        super(context);
        init(context);
    }

    public SokoViewClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoViewClass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        bmp = new Bitmap[6];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.boxok);

        this.hero = new Hero(level);
    }

    float xDown;
    float yDown;
    int touches = 0;
    int xDisplay;
    int yDisplay;
    private boolean standingOnCross = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                Log.i("sokoView", "Hero from 88: ("+this.hero.getPosition()+")");

                touches++;
                xDown = event.getX();//0-1100?
                yDown = event.getY();//0-2000?
                Toast.makeText(getContext(), "dotk X:"+xDown+", Y:"+yDown, Toast.LENGTH_SHORT).show();

                if(xDown>700&&yDown<1700&&yDown>300) move("right");
                if(xDown<500&&yDown<1700&&yDown>300) move("left");
                if(yDown>1700) move("down");
                if(yDown<300) move("up");

                break;
            }
        }
        return super.onTouchEvent(event);
    }

    private void move(String d){
                level[hero.getPosition()] = standingOnCross ? E.CROSS : E.EMPTY;//da zpatky krizek pokud tam stal
                switch(d){
                    case "right":

                        hero.moveRight();
                        Toast.makeText(getContext(), "moving right", Toast.LENGTH_SHORT).show();
                        Log.i("sokoView", "Hero from 113: ("+hero.getPosition()+")");

                        break;
                    case "left":
                        hero.moveLeft();
                        Toast.makeText(getContext(), "moving left", Toast.LENGTH_SHORT).show();
                        break;
                    case "up":
                        hero.moveUp();
                        Toast.makeText(getContext(), "moving up", Toast.LENGTH_SHORT).show();
                        break;
                    case "down":
                        hero.moveDown();
                        Toast.makeText(getContext(), "moving down", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getContext(), "wth", Toast.LENGTH_SHORT).show();
                        break;
                }


        standingOnCross = (level[hero.getPosition()] == E.CROSS);
        level[hero.getPosition()] = E.HERO;//presune postavicku na aktualni polohu

        invalidate(); //překreslení
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;

        xDisplay = (int) w;
        yDisplay = (int) h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }

    }
}