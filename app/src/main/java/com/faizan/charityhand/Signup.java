package com.faizan.charityhand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Signup extends AppCompatActivity {

    private Button openLogin;
    private Button cancelBtn;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://charity-hand-c3709-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        openLogin = findViewById(R.id.loginButton);
        cancelBtn = findViewById(R.id.cancelButton);
        final EditText fName = findViewById(R.id.fname);
        final EditText uName =findViewById(R.id.username);
        final EditText mail = findViewById(R.id.mail);
        final EditText password = findViewById(R.id.password);
        final EditText cPassword = findViewById(R.id.conPassword);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        cPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        openLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//              Call registration function
                registration(fName, uName, mail, password, cPassword);}
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    //  Create registration function
    public void registration(EditText name, EditText uName, EditText mail,
                             EditText password, EditText cPassword){

        final String txt_fName = name.getText().toString();
        final String txt_uName = uName.getText().toString();
        final String txt_mail = mail.getText().toString();
        final String txt_password = password.getText().toString();
        final String txt_cPassword = cPassword.getText().toString();

        boolean sCharacter = txt_uName.contains("@") || txt_uName.contains("#") || txt_uName.contains("$")
                || txt_uName.contains("%") || txt_uName.contains("&") || txt_uName.contains("*")
                || txt_uName.contains("!") || txt_uName.contains("^");

//      Check any input is empty
        if (txt_fName.isEmpty() || txt_uName.isEmpty() || txt_mail.isEmpty() || txt_password.isEmpty() ) {
            Toast.makeText(Signup.this, "One or more field is empty", Toast.LENGTH_LONG).show();
        }

//      Check any special character present in the username
        else if (sCharacter) {
            Toast.makeText(Signup.this, "Type username without special character",
                    Toast.LENGTH_LONG).show();
        }

//      check email format
        else if (!txt_mail.contains("@")) {
            Toast.makeText(Signup.this, "Wrong Email Address", Toast.LENGTH_LONG).show();
        }

//      Check password's length
        else if (txt_password.length() < 8) {
            Toast.makeText(Signup.this, "Password should contains minimum 8 Characters",
                    Toast.LENGTH_LONG).show();
        }

//      Check password match with confirm password
        else if (!txt_password.equals(txt_cPassword)) {
            Toast.makeText(Signup.this, "Confirm Password not match with password",
                    Toast.LENGTH_LONG).show();
        }

        else {
            databaseReference.child("Users").addListenerForSingleValueEvent
                    (new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

//                  Check username exist or not
                            if (snapshot.child("Registration").hasChild(txt_uName)){
                                Toast.makeText(Signup.this, "Username already exist", Toast.LENGTH_LONG).show();
                            }

//                  Register data in the database
                            else {
                                databaseReference.child("Users").child("Registration").child(txt_uName).
                                        child("First Name").setValue(txt_fName);
                                databaseReference.child("Users").child("Registration").child(txt_uName).
                                        child("Mail Address").setValue(txt_mail);
                                databaseReference.child("Users").child("Registration").child(txt_uName).
                                        child("Password").setValue(txt_password);

                                Toast.makeText(Signup.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {



                        }
                    });

        }
    }
    public void cancel(){
        Intent cancelSignup = new Intent(this, Login.class);
        startActivity(cancelSignup);
    }

}