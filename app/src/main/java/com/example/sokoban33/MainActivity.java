package com.example.sokoban33;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GameObject hero = new GameObject(E.startX,E.startY);

        Button buttUp = findViewById(R.id.UP);
        Button buttDown = findViewById(R.id.DOWN);
        Button buttLeft = findViewById(R.id.LEFT);
        Button buttRight = findViewById(R.id.RIGHT);

        buttUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero.move(E.UP);
            }
        });
        buttDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero.move(E.DOWN);
            }
        });
        buttLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero.move(E.LEFT);
            }
        });
        buttRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero.move(E.RIGHT);
            }
        });
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

}
