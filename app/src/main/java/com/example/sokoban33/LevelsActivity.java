package com.example.sokoban33;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LevelsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_levels);
        Log.d("soko", "yes");

    }

    public void setLevel0(View view){
        E.actualLevel = 0;
        E.plz();
        Intent i = new Intent(this, SokoActivity.class);
        startActivity(i);
    }

    public void setLevel1(View view){
        E.actualLevel = 1;
        E.plz();
        Intent i = new Intent(this, SokoActivity.class);
        startActivity(i);
    }

    public void setLevel2(View view){
        E.actualLevel = 2;
        E.plz();
        Intent i = new Intent(this, SokoActivity.class);
        startActivity(i);
    }
}
