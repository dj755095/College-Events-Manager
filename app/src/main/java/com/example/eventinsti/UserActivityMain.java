package com.example.eventinsti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivityMain extends AppCompatActivity {

    SearchView searchView;
    EventAdapter eventAdapter;

    String sbranch="";
    ImageView profileFromUser;
    String nameF,emailF,usernameF,passwordF;

    RecyclerView recyclerView;
    List<UploadHelperClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
//    Button signoutBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        fetchDataFromIntent();
        profileFromUser = findViewById(R.id.profileFromUser);
//        signoutBtn2 = findViewById(R.id.signoutBtn2);

        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        //Branch Identification
        identifyBranch();

        recyclerView = findViewById(R.id.recyclerViewUser);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserActivityMain.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO
                searchList(newText);
                return true;
            }
        });


        profileFromUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivityMain.this, ProfileActivity.class);
                intent.putExtra("name", nameF);
                intent.putExtra("email", emailF);
                intent.putExtra("username", usernameF);
                intent.putExtra("password", passwordF);
                startActivity(intent);
            }
        });
//        signoutBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(UserActivityMain.this,LoginActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
    }
    public void searchList(String text){
        ArrayList<UploadHelperClass> searchList = new ArrayList<>();
        for (UploadHelperClass dataClass: dataList){
            if (dataClass.getEventTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        eventAdapter.searchDataList(searchList);
    }

    private void setReferenceToDATABSE() {
        Toast.makeText(this, "Current Branch is"+sbranch, Toast.LENGTH_SHORT).show();
        switch (sbranch) {
            case "IT/CO":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsIT");
                Toast.makeText(this, "Event is set to it", Toast.LENGTH_SHORT).show();
                break;
            case "Mechanical":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsME");
                break;
            case "Electrical":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsEJ");
                break;
            case "Civil":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsCE");
                break;
            case "Production":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsPRO");
                break;
            case "Textile":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsTXT");
                break;
            case "ELT":
                databaseReference = FirebaseDatabase.getInstance().getReference("eventsELT");
                break;
            default:
                databaseReference = FirebaseDatabase.getInstance().getReference("Institute Events");
                Toast.makeText(this, "Last one is called", Toast.LENGTH_SHORT).show();
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivityMain.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        eventAdapter = new EventAdapter(UserActivityMain.this,dataList);
        recyclerView.setAdapter(eventAdapter);
        //Todo branch checking
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

    }

    public void identifyBranch(){
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Student Data");
        Query checkUserDatabasew = reference2.orderByChild("username").equalTo(usernameF);
        checkUserDatabasew.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    sbranch = snapshot.child(usernameF).child("studyBranch").getValue(String.class);
                    setReferenceToDATABSE();
                    Toast.makeText(UserActivityMain.this, "Branch is"+sbranch, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void fetchDataFromIntent(){
        Intent intent = getIntent();
        nameF = intent.getStringExtra("name");
        emailF = intent.getStringExtra("email");
        usernameF = intent.getStringExtra("username");
        passwordF = intent.getStringExtra("password");
    }
}