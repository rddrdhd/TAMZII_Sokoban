package com.example.sokoban33;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SokoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.levels:
                // do something
                return true;
            case R.id.reset:

                View sokoV = findViewById(R.id.sokoView);
                E.actualLevelArray = E.LEVELS[E.actualLevel].clone();
                E.resetingLevel = true;
                sokoV.invalidate();
                return true;

            default:
                return super.onContextItemSelected(item);

        }
    }
}
