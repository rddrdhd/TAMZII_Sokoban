package com.example.sokoban33;

public class E {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int BOX = 2;
    public static final int CROSS = 3;
    public static final int HERO = 4;
    public static final int BOXOK = 5;


    public static int[][] LEVELS = {
            {//0
            1,1,1,1,1,1,1,1,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,4,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0}
    };

    public static int[] actualLevel = LEVELS[0];
}
