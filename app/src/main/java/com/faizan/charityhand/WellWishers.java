package com.faizan.charityhand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WellWishers extends AppCompatActivity {


    private ListView wellWishersList;

//    define reference for the database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://charity-hand-c3709-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_wishers);

        wellWishersList = findViewById(R.id.wellWishersList);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_layout, list);
        wellWishersList.setAdapter(adapter);


//    define Path for saving the data

        databaseReference.child("Users").child("Wellwishers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
//          retrieve all value using for each loop
                for (DataSnapshot snap: snapshot.getChildren()){
                    Information information = snap.getValue(Information.class);
                    String retrieveData = "Name: " + information.getName() + "\n" + "Address: "
                            + information.getAddress() + "\n" + "Phone: " + information.getPhone_no();
                    list.add(retrieveData);
                }
//              update data in the app after save the data in the firebase
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}