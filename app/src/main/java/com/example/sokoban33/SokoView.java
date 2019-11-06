package com.example.sokoban33;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {

                touches++;
                xDown = event.getX();//0-1100?
                yDown = event.getY();//0-2000?
                Toast.makeText(getContext(), "dotk X:"+xDown+", Y:"+yDown, Toast.LENGTH_SHORT).show();
                if(xDown>700&&yDown<1700&&yDown>300) {
                   move("right");
                }
                if(xDown<500&&yDown<1700&&yDown>300){
                    move("left");
                }
                if(yDown>1700){
                    move("down");
                }
                if(yDown<300){
                    move("up");
                }

                break;
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean standingOnCross = false;
    private void move(String d){
        int newHeroCoor = getCoor(heroX, heroY);


        if(Objects.equals(d, "right")
                || Objects.equals(d, "left")
                || Objects.equals(d, "up")
                || Objects.equals(d, "down")){

            switch(d){
                case "right":
                    if(notWall("right")){
                        level[getCoor(heroX, heroY)] = standingOnCross?3:0;
                        newHeroCoor=getCoor(++heroX, heroY);
                    }
                    break;
                case "left":
                    if(notWall("left")){
                        level[getCoor(heroX, heroY)] = standingOnCross?3:0;
                        newHeroCoor=getCoor(--heroX, heroY);
                    }
                    break;
                case "up":
                    if(notWall("up")){
                        level[getCoor(heroX, heroY)] = standingOnCross?3:0;
                        newHeroCoor=getCoor(heroX, --heroY);
                    }
                    break;
                case "down":
                    if(notWall("down")){
                        level[getCoor(heroX, heroY)] = standingOnCross?3:0;
                        newHeroCoor=getCoor(heroX, ++heroY);
                    }
                    break;
                default:
                    Toast.makeText(getContext(), "default", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        standingOnCross = (level[newHeroCoor] == 3); //bool
        level[newHeroCoor] = 4;




        invalidate(); //překreslení
    }

    private boolean notWall(String direction){
       switch(direction){
            case "left":
                if (level[heroCoor()-1] == 1) return false;
                return true;
            case "right":
                if (level[heroCoor()+1] == 1) return false;
                return true;
            case "up":
                if (level[getCoor(heroX, heroY-1)] == 1) return false;
                break;
            case "down":
                if (level[getCoor(heroX, heroY+1)] == 1) return false;
                break;
            default:
                return true;
        }

        return true;
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