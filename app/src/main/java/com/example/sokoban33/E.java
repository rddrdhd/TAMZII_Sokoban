package com.example.sokoban33;

public class E {
    //globals
    public static int actualLevel = 2;
    public static boolean resetingLevel = false;
    public static int touches = 0;

    //constants
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

    public static int[] actualLevelArray;
    public static int startX;
    public static int startY;



    public static final int[][] STARTING_POSITIONS = {
            {6,4},//0. level
            {6,4},//1. level
            {5,4},//2. level
    };


    public static final int[][] LEVELS = {
        {//0. level
            1,1,1,1,1,1,1,1,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,0,2,3,0,0,1,0,1,0,
            1,0,1,0,0,0,0,0,1,0,
            1,0,0,0,0,0,4,0,1,0,
            1,0,1,0,0,0,0,0,1,0,
            1,0,0,0,0,0,1,0,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0
        }, {//1. level
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
        }, {//2. level
            1,1,0,0,3,3,1,1,1,1,
            1,3,0,0,2,0,1,1,1,1,
            1,1,0,2,0,0,1,1,1,1,
            1,1,1,0,2,2,1,1,1,1,
            1,1,3,2,2,4,0,1,1,1,
            1,1,3,0,2,2,0,0,0,1,
            1,1,0,1,1,0,0,0,1,1,
            1,1,3,1,3,3,0,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1
        },
    };

    public static void plz(){
    actualLevelArray = LEVELS[actualLevel].clone();

    //starting positions set
    startX = STARTING_POSITIONS[actualLevel][0];
    startY = STARTING_POSITIONS[actualLevel][1];
    }
}
