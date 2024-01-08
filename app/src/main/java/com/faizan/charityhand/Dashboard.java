package com.faizan.charityhand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private ImageView imageOurLocation, imageDonate, imageProject, imageWellWisher;
    private TextView textOurLocation , textDonate, textProject, textWellWisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageDonate = findViewById(R.id.imageDonate);
        imageOurLocation = findViewById(R.id.imageLocation);
        textOurLocation = findViewById(R.id.textLocation);
        textDonate = findViewById(R.id.textDonate);
        imageProject = findViewById(R.id.imageProject);
        textProject = findViewById(R.id.textProject);
        imageWellWisher = findViewById(R.id.imageDonor);
        textWellWisher = findViewById(R.id.textDonor);

        imageDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { donation(); };
        });

        textDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { donation(); }
        });

        imageOurLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ourLocation(); };
        });

        textOurLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ourLocation(); }
        });

        imageWellWisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { wellWishers(); };
        });

        textWellWisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { wellWishers(); }
        });

        imageProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ourProject(); };
        });

        textProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ourProject(); }
        });
    }

    public void donation(){
        Intent navigateDonation = new Intent(this, Donation.class);
        startActivity(navigateDonation);
    }

    public void ourLocation(){
        Intent navigateOurLocation = new Intent(this, OurLocation.class);
        startActivity(navigateOurLocation);
    }

    public void wellWishers(){
        Intent navigateWellWishers = new Intent(this, WellWishers.class);
        startActivity(navigateWellWishers);
    }

    public void ourProject(){
        Intent navigateOurProject = new Intent(this, OurProject.class);
        startActivity(navigateOurProject);
    }

}