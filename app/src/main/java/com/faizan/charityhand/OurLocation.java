package com.faizan.charityhand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OurLocation extends AppCompatActivity {

    private Button BtnConDetail, BtnLocation, BtnLiveLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_location);

        BtnConDetail =findViewById(R.id.BtnConDetails);
        BtnLocation = findViewById(R.id.BtnLocation);
        BtnLiveLocation = findViewById(R.id.BtnLiveLocation);

        BtnConDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conDetail();
            }
        });

        BtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsLocation();
            }
        });

        BtnLiveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveLocation();
            }
        });
    }

    private void liveLocation() {
        Intent intent = new Intent(this, LocationDistance.class);
        startActivity(intent);
    }

    private void conDetail() {
        Intent intent = new Intent(this, Contact.class);
        startActivity(intent);
    }
    private void gpsLocation() {
        Intent navigateGpsLocation = new Intent(this, OurGpsLocation.class);
        startActivity(navigateGpsLocation);
    }
}