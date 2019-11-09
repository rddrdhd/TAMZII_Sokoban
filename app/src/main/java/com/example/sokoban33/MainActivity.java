package com.example.sokoban33;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_game);
            }
        });

        final Button buttonResume = findViewById(R.id.buttonResume);
        buttonResume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(this, SokoView.class);
                // String message = "idk";
                // intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
                Toast.makeText(getApplicationContext(), "nope", Toast.LENGTH_LONG).show();
            }
        });
    }

}
