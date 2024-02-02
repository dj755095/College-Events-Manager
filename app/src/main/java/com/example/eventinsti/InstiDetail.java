package com.example.eventinsti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InstiDetail extends AppCompatActivity {
    private TextView usernameTextView;
    private TextView instituteNameTextView;
    private TextView instituteCodeTextView;
    private TextView instituteAddressTextView;
    private TextView institutePhoneTextView;
    private TextView collegeTypeTextView;
    private TextView collegeFieldsTextView;
    private TextView degreeTypeTextView;
    private TextView branchTypeTextView;
    String username="";
    Button approve;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insti_detail);
        approve = findViewById(R.id.approveInstiBtns);
        usernameTextView = findViewById(R.id.text_username);
        instituteNameTextView = findViewById(R.id.text_institute_name);
        instituteCodeTextView = findViewById(R.id.text_institute_code);
        instituteAddressTextView = findViewById(R.id.text_institute_address);
        institutePhoneTextView = findViewById(R.id.text_institute_phone);
        collegeTypeTextView = findViewById(R.id.text_college_type);
        collegeFieldsTextView = findViewById(R.id.text_college_fields);
        degreeTypeTextView = findViewById(R.id.text_degree_type);
        branchTypeTextView = findViewById(R.id.text_branch_type);

        getIntentdata();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username;
                String status = "Approved";
                DatabaseReference databaseReference1;
                databaseReference1 = FirebaseDatabase.getInstance().getReference("Instiusers");
                Query checkUserDatabase = databaseReference1.orderByChild("username").equalTo(name);
                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            databaseReference1.child(name).child("verifyStats").setValue(status);
                            Toast.makeText(InstiDetail.this, "Approved", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(InstiDetail.this, AdminActivity.class);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(i);
                            overridePendingTransition(0, 0);
                        }else {
                            Toast.makeText(InstiDetail.this, "Does Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent = new Intent(InstiDetail.this,AdminActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getIntentdata() {
        Intent inte = getIntent();
        username = inte.getStringExtra("username");
        Toast.makeText(this, "intent user"+username, Toast.LENGTH_SHORT).show();

        fetchingMethod();
    }

    private void fetchingMethod() {
        String intentUsername = username.trim(); // Trim the username from the Intent
        reference = FirebaseDatabase.getInstance().getReference("Institute Data");
        Query checkUserDatabase = reference.orderByChild("susername").equalTo(intentUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        HelperClassSignupInstitute hp1 = childSnapshot.getValue(HelperClassSignupInstitute.class);
                        if (hp1 != null) {

                            usernameTextView.setText(hp1.getSusername());
                            instituteNameTextView.setText(hp1.getInstituteName());
                            instituteCodeTextView.setText(hp1.getInstituteCode());
                            instituteAddressTextView.setText(hp1.getInstituteAddress());
                            institutePhoneTextView.setText(hp1.getInstitutePhone());
                            collegeTypeTextView.setText(hp1.getCollegeType());
                            collegeFieldsTextView.setText(hp1.getCollegeFields());
                            degreeTypeTextView.setText(hp1.getDegreeType());
                            branchTypeTextView.setText(hp1.getBranchType());
                        }
                    }
                } else {
                    Toast.makeText(InstiDetail.this, "Nothing found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

}