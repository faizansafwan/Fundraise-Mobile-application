package com.faizan.charityhand;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BankDonation extends AppCompatActivity {

//  initialize the variable
    private ImageView uploadImage;
    private Button uploadImageBtn;

    FirebaseStorage storage;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_donation);

        uploadImage = findViewById(R.id.imageUpload);
        uploadImageBtn = findViewById(R.id.uploadBtn);
        storage = FirebaseStorage.getInstance();

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcontent.launch("image/*");
            }
        });

        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeOnFirebase();
            }
        });


    }
// function for upload the image to the firebase storage
    private void storeOnFirebase(){
        if (imageUri != null){

            // Making the date and time format and getting the current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentDate = new Date();
            String fileName = dateFormat.format(currentDate);
            StorageReference reference = storage.getReference().child("image/" + UUID.randomUUID().toString() + "  " +  fileName);

            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(BankDonation.this, "Image uploaded successful", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(BankDonation.this, "upload failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    // Select a image from mobile storage
    ActivityResultLauncher<String> getcontent = registerForActivityResult(new
            ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {

            if (result != null){
                uploadImage.setImageURI(result);
                imageUri = result;
            }
        }

    });


    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}