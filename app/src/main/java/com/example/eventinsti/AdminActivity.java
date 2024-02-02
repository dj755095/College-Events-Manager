package com.example.eventinsti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdminAdapter adminAdapter;
    ArrayList<HelperClass2> list;
    Button signoutBtn;
    //Approval
    EditText instiName;
    Button approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        instiName = findViewById(R.id.instiNameEdit);
        approve = findViewById(R.id.approveBtn);
        signoutBtn = findViewById(R.id.signoutBtn);

        recyclerView = findViewById(R.id.instiUserRecycler);
        databaseReference = FirebaseDatabase.getInstance().getReference("Instiusers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adminAdapter = new AdminAdapter(this, list);
        recyclerView.setAdapter(adminAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    HelperClass2 helperClass2 = dataSnapshot.getValue(HelperClass2.class);
                    list.add(helperClass2);
                }
                adminAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Copy this code to new DetailInstitute class then perform same actions
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = instiName.getText().toString();
                String status = "Approved";

                Query checkUserDatabase = databaseReference.orderByChild("username").equalTo(name);
                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            databaseReference.child(name).child("verifyStats").setValue(status);
                            Toast.makeText(AdminActivity.this, "Approved", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(AdminActivity.this, AdminActivity.class);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(i);
                            overridePendingTransition(0, 0);
                        }else {
                            Toast.makeText(AdminActivity.this, "Does Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}