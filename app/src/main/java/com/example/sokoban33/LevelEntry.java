package com.example.sokoban33;

import android.graphics.Bitmap;

import java.io.Serializable;

public class LevelEntry implements Serializable {
    public String name;
    public int num;

    LevelEntry(String name, int num){
        this.name = name;
        this.num = num;
    }
}
