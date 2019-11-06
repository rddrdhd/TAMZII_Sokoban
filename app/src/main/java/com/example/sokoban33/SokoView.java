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

    private void move(String direction){
        int x = heroX;
        int y = heroY;

        level[getCoor(heroX, heroY)] = 0;

        switch(direction){
            case "right":
                level[getCoor(heroX+1, heroY)] = 4;
                heroX++;
                break;
            case "left":
                level[getCoor(heroX-1, heroY)] = 4;
                heroX--;
                break;
            case "up":
                level[getCoor(heroX, heroY-1)] = 4;
                heroY--;
                break;
            case "down":
                level[getCoor(heroX, heroY+1)] = 4;
                heroY++;
                break;
            default:

                Toast.makeText(getContext(), "default", Toast.LENGTH_SHORT).show();
                break;


        }


        invalidate(); //překreslení
    }

    private boolean standingOnCross = false;
    private boolean canMove(String direction){
        if (heroX<6&&direction=="up") return false;
       /* switch(direction){
            case "left":
                if (level[getCoor(heroX-1, heroY)])
                break;
            case "right":
                break;
            case "up":
                break;
            case "down":
                break;
            default:
                return true;
        }*/

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
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