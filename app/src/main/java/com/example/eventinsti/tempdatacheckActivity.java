package com.example.eventinsti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.eventinsti.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ortiz.touchview.TouchImageView;

public class tempdatacheckActivity extends AppCompatActivity {
    TextView text;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempdatacheck);
        TouchImageView img = findViewById(R.id.uploadImage);
        img.setImageResource(R.drawable.ic_baseline_email_24);
//        text = findViewById(R.id.textView2);
//        reference = FirebaseDatabase.getInstance().getReference("users");
//
//        String em = "dj@gmail.com";
//        Query query = reference.orderByChild("email").equalTo(em);
//
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Check if any data matches the query
//                if (dataSnapshot.exists()) {
//                    // Assuming there's only one matching user in this case
//                    DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();
//
//                    // Retrieve data for the user
//                    String username = userSnapshot.child("username").getValue(String.class);
//                    String name = userSnapshot.child("name").getValue(String.class);
//                    String password = userSnapshot.child("password").getValue(String.class);
//
//                    // Log the data
//                    Log.d("FirebaseData", "Username: " + username);
//                    Log.d("FirebaseData", "Name: " + name);
//                    Log.d("FirebaseData", "Password: " + password);
//
//                    // Update the TextView with the retrieved data
//                    String userData = "Username: " + username + "\nName: " + name + "\nPassword: " + password;
//                    text.setText(userData);
//
//                } else {
//                    // No matching data found
//                    Log.d("FirebaseData", "User not found");
//                    text.setText("User not found");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle errors
//                Log.e("FirebaseError", "Database error: " + databaseError.getMessage());
//                text.setText("Database error: " + databaseError.getMessage());
//            }
//        });

    }
}
