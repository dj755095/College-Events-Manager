package com.example.eventinsti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InstituteActivity extends AppCompatActivity {
    TextView InstituteUserName,vStatus;
    String UserName,svStatus,profileURL;
    String nameF,emailF,passwordF;

    Button fab;
    ImageView profileActivityFromInsti;

    RecyclerView recyclerView;
    List<UploadHelperClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute);
        InstituteUserName = findViewById(R.id.InstituteUserName);
        vStatus = findViewById(R.id.vStatus);
        fab = findViewById(R.id.fab);
        profileActivityFromInsti = findViewById(R.id.profileActivityFromInsti);
        recyclerView = findViewById(R.id.recyclerView);
        fetchDataFromIntent();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(InstituteActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        //get Data of name of insti
        InstituteUserName.setText(UserName);
        //For verify status
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Instiusers");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(UserName);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                svStatus = snapshot.child(UserName).child("verifyStats").getValue(String.class);
                profileURL = snapshot.child(UserName).child("profileURL").getValue(String.class);
                vStatus.setText(svStatus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Code For Recycler Viewwwww
        AlertDialog.Builder builder = new AlertDialog.Builder(InstituteActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        EventAdapter eventAdapter = new EventAdapter(InstituteActivity.this,dataList);
        recyclerView.setAdapter(eventAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Institute Events");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    UploadHelperClass dataClass = itemSnapshot.getValue(UploadHelperClass.class);
                    dataList.add(dataClass);
                }
                eventAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        //Profile activity
        profileActivityFromInsti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstituteActivity.this, ProfileActivity.class);
                intent.putExtra("name", nameF);
                intent.putExtra("email", emailF);
                intent.putExtra("username", UserName);
                intent.putExtra("password", passwordF);
                startActivity(intent);
            }
        });

        //Fabb onclick redirect to Upload image section
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InstituteActivity.this, UploadActivity.class);
                i.putExtra("username",UserName);
                i.putExtra("verifyStatus",svStatus);
                i.putExtra("profileURL",profileURL);
                startActivity(i);
            }
        });
    }
    public void fetchDataFromIntent(){
        Intent intent = getIntent();
        nameF = intent.getStringExtra("name");
        emailF = intent.getStringExtra("email");
        passwordF = intent.getStringExtra("password");
        UserName = intent.getStringExtra("Iusername");
    }




}