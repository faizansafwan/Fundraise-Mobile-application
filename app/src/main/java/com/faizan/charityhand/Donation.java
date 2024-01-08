package com.faizan.charityhand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Donation extends AppCompatActivity {

    private Button bank, creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        bank = findViewById(R.id.bank);
        creditCard = findViewById(R.id.creditCard);

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { setBank();}
        });

        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { setCreditCard();}
        });
    }

    public void setBank(){
        Intent intent = new Intent(this, BankDonation.class);
        startActivity(intent);
    }

    public void setCreditCard(){
        Intent intent = new Intent(this, CreditCardDonation.class);
        startActivity(intent);
    }
}