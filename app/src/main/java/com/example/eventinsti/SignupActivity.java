package com.example.eventinsti;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignupActivity extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton1;

    LinearLayout ly1;

    FirebaseDatabase database;
    DatabaseReference reference,reference2;

    Button adminButton, instituteButton, signupButton;
    TextView textView;
    TextView textView1,textView2;
    String type="s";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //adminButton = findViewById(R.id.adminButton);

        instituteButton = findViewById(R.id.instituteButton);
        signupButton = findViewById(R.id.signupButton);
        textView = findViewById(R.id.textView);
        //signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton1 = findViewById(R.id.signup_button);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        ly1 = findViewById(R.id.ly1);
        ly1.setVisibility(View.GONE);

        signupButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    uploadMethod();
                }

            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onButtonClick(View view) {
        // Change background for all buttons to unselected
//        adminButton.setBackgroundResource(R.drawable.unselected_background);
//        instituteButton.setBackgroundResource(R.drawable.unselected_background);
//        signupButton.setBackgroundResource(R.drawable.unselected_background);

        int color = ContextCompat.getColor(this, R.color.lavender);
        int defaultColor = ContextCompat.getColor(this,R.color.white);
//        adminButton.setTextColor(defaultColor);
//        instituteButton.setTextColor(defaultColor);
//        signupButton.setTextColor(defaultColor);

//        adminButton.setVisibility(View.VISIBLE);
        instituteButton.setVisibility(View.VISIBLE);
        signupButton.setVisibility(View.VISIBLE);
        ly1.setVisibility(View.VISIBLE);


        // Change the clicked button's background
        switch (view.getId()) {
//            case R.id.adminButton:
//                adminButton.setBackgroundResource(R.drawable.selected_background);
//                //adminButton.setTextColor(color);
//                adminButton.setVisibility(View.GONE);
//                textView.setText("Admin Login");
//                type = "a";
//                break;
            case R.id.instituteButton:
                //instituteButton.setTextColor(color);
                instituteButton.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView.setText("Institute Signup");
                signupButton.setText("Switch to Student");

                type = "i";
                break;
            case R.id.signupButton:
//                instituteButton.setVisibility(View.VISIBLE);
//
//                signupButton.setVisibility(View.VISIBLE);
                //signupButton.setTextColor(color);
                signupButton.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView.setText("Student Signup");
                instituteButton.setText("Switch to Institue");
                type = "s";
                break;
        }
    }



    private void uploadMethod() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");


        String name = type;
        String email = signupEmail.getText().toString();
        String username = signupUsername.getText().toString();
        String password = signupPassword.getText().toString();
        String verifyStats = "Not Verified";

        HelperClass helperClass = new HelperClass(name, email, username, password);
        reference.child(username).setValue(helperClass);
        Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
        if(name.equals("i")){
            Intent intent = new Intent(SignupActivity.this, SignupInstitute.class);
            intent.putExtra("username",username);
            intent.putExtra("verifyStats",verifyStats);
            startActivity(intent);
            finish();
        }else if(name.equals("s")){
            Intent intent = new Intent(SignupActivity.this, SignupStudent.class);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    private boolean validateInput() {
        boolean isValid = true;

        // Validate Password
        String password = signupPassword.getText().toString().trim();
        if (password.isEmpty() || password.length() < 6) {
            signupPassword.setError("Password must be at least 6 characters long");
            signupPassword.requestFocus();
            isValid = false;
        }


        // Validate Username
        String username = signupUsername.getText().toString().trim();
        if (username.isEmpty()) {
            signupUsername.setError("Username cannot be empty");
            signupUsername.requestFocus();
            isValid = false;
        }

        // Validate Email
        String email = signupEmail.getText().toString().trim();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Enter a valid email address");
            signupEmail.requestFocus();
            isValid = false;
        }


        return isValid;
    }


}
