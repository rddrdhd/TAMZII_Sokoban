package com.example.sokoban33;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SokoActivity extends AppCompatActivity {
Button buttUndo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
/*
        buttUndo = findViewById(R.id.butt);
        buttUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E.undoig = true;
            }
        });
*/
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
            case R.id.back:
                // do something
                return true;
            case R.id.resume:
                // do something
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }

    public void undo(View view){
        E.undoig = true;
    }
}
