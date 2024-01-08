package com.faizan.charityhand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button button_signup;
    private Button button_login;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://charity-hand-c3709-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = findViewById(R.id.loginButton);
        button_signup = findViewById(R.id.signupButton);
        final EditText uName = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Called the function to open signup page
                openSignup();
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Called the function to open signup page
                signin(uName, password);
            }
        });
    }

//  Create function signin
    private void signin(EditText username, EditText password) {
        final String txt_uName = username.getText().toString();
        final String txt_password = password.getText().toString();

        boolean verifyUsername = txt_uName.isEmpty() || txt_password.isEmpty();

//      Check input field empty or not
        if (verifyUsername){
            Toast.makeText(Login.this, "Please Enter your Username or password",
                    Toast.LENGTH_LONG).show();
        }
        else {
            databaseReference.child("Users").addListenerForSingleValueEvent
                    (new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

//                  Verify the username
                            if (snapshot.child("Registration").hasChild(txt_uName)){

                                final String getPassword = snapshot.child("Registration").child(txt_uName).
                                        child("Password").getValue(String.class);

//                      Check the entered password with database
                                if (getPassword.equals(txt_password)){
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Login.this, Dashboard.class));
                                    finish();
                                }
//                      if the password is wrong then else part will execute
                                else {
                                    Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_LONG).show();
                                }

                            }

//                  If the username is wrong, else part will execute
                            else {
                                Toast.makeText(Login.this, "Wrong username", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }


    //    Open Signup page
    public void openSignup(){
        Intent navigateSignup = new Intent(this, Signup.class);
        startActivity(navigateSignup);
    }



}