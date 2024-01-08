package com.faizan.charityhand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Theme extends AppCompatActivity {

    Timer delay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        delay = new Timer();
        delay.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Theme.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}