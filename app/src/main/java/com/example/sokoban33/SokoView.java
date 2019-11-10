package com.example.sokoban33;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{

    Bitmap[] bmp;

    int[] originalLevel = E.actualLevelArray.clone();

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    float xDown;
    float yDown;

    int touches = 0;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touches++;
            xDown = event.getX();
            yDown = event.getY();
/*
            if (xDown > 500 && yDown < 1500 && yDown > 500) hero.move(E.RIGHT);
            else if (xDown < 500 && yDown < 1500 && yDown > 500) hero.move(E.LEFT);
            else if (yDown > 1500) hero.move(E.DOWN);
            else if (yDown < 500) hero.move(E.UP);
*/



            fixArray();
            invalidate(); //redraw array

        }
        return super.onTouchEvent(event);
    }

    private void fixArray(){
        for(int i = 0; i< originalLevel.length; i++){
            if(originalLevel[i]==E.CROSS&&E.actualLevelArray[i]==E.EMPTY) E.actualLevelArray[i]=E.CROSS;
            if(originalLevel[i]==E.CROSS&&E.actualLevelArray[i]==E.BOX) E.actualLevelArray[i]=E.BOXOK;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = (int) (h /lx);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[E.actualLevelArray[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }

    }
}
