package com.example.sokoban33;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by kru13 on 12.10.16.
 */

public class SokoView extends View{

    Bitmap[] bmp;

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    int heroX = 6;
    int heroY = 4;


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

    private final int originalLevel[] = level;

    int heroPos = Arrays.binarySearch(level, 4);

    private void crossLevel(int[]level, int[]originalLevel) {
        for(int i=0; i<level.length; i++){
            if(originalLevel[i]==E.CROSS && level[i]==E.EMPTY) level[i] = E.CROSS;

        }
    }

    private int getCoor(int x, int y){
        return y*10+x;
    }

    private int heroCoor() {return heroY*10+heroX;}



    public SokoView(Context context) {
        super(context);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
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

    }

    float xDown;
    float yDown;
    int touches = 0;
    int xDisplay;
    int yDisplay;
    private boolean standingOnCross = false;
    private boolean boxOnCross = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touches++;
            xDown = event.getX();//0-1100?
            yDown = event.getY();//0-2000?
            Toast.makeText(getContext(), "dotk X:" + xDown + ", Y:" + yDown, Toast.LENGTH_SHORT).show();

            if (xDown > 700 && yDown < 1700 && yDown > 300) move("right");
            if (xDown < 500 && yDown < 1700 && yDown > 300) move("left");
            if (yDown > 1700) move("down");
            if (yDown < 300) move("up");
        }
        return super.onTouchEvent(event);
    }

    private void move(String d){
        int newHeroCoor = getCoor(heroX, heroY);
            if(canGo(d)){//pokud neleze do zdi
                if(isPushigBox(d)) {
                    pushBox(d);
                }


                level[heroCoor()] = standingOnCross ? E.CROSS : E.EMPTY;//da zpatky krizek pokud tam stal
                switch(d){
                    case "right":
                        newHeroCoor=getCoor(++heroX, heroY);
                        break;
                    case "left":
                        newHeroCoor=getCoor(--heroX, heroY);
                        break;
                    case "up":
                        newHeroCoor=getCoor(heroX, --heroY);
                        break;
                    case "down":
                        newHeroCoor=getCoor(heroX, ++heroY);
                        break;
                    default:
                        Toast.makeText(getContext(), "wth", Toast.LENGTH_SHORT).show();
                        break;
                }
                if(isPushigBox(d)) {
                    standingOnCross = boxOnCross;
                    boxOnCross = false;
                }
            }


        crossLevel(level, originalLevel);
        standingOnCross = (level[newHeroCoor] == E.CROSS);
        level[newHeroCoor] = 4;//presune postavicku na aktualni polohu
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

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }
    }

    private void pushBox(String direction){
        if(canGo(direction)){
            int boxcoor;

            switch(direction){
                case "left":
                    boxcoor = getCoor(heroX-2,heroY);
                    break;
                case "right":
                    boxcoor = getCoor(heroX+2,heroY);
                    break;
                case "up":
                    boxcoor = getCoor(heroX,heroY-2);
                    break;
                case "down":
                    boxcoor = getCoor(heroX,heroY+2);
                    break;
                default:
                    boxcoor = heroCoor();
            }
            boxOnCross = (level[boxcoor] == E.CROSS);
            level[boxcoor] = E.BOX;
        }
    }

    private boolean isPushigBox(String direction){
        switch(direction){
            case "left":
                return level[heroCoor() - 1] == 2;
            case "right":
                return level[heroCoor() + 1] == 2;
            case "up":
                return level[getCoor(heroX, heroY - 1)] == 2;
            case "down":
                return level[getCoor(heroX, heroY + 1)] == 2;
            default:
                return false;
        }
    }

    private boolean canGo(String direction){
        switch(direction){
            case "left":
                return level[heroCoor() - 1] != 1
                        && heroX != 1
                        && (!(heroCoor()%10 == 1 && isPushigBox(direction)));
            case "right":
                return level[heroCoor() + 1] != 1
                        && heroX != 9
                        && (!(heroCoor()%10 == 8 && isPushigBox(direction)));
            case "up":
                return level[getCoor(heroX, heroY - 1)] != 1
                        && heroY != 0
                        && (heroY != 1 && isPushigBox(direction));
            case "down":
                return level[getCoor(heroX, heroY + 1)] != 1
                        && heroY != 9
                        && (heroY != 8 && isPushigBox(direction));
            default:
                return true;
        }
    }
}