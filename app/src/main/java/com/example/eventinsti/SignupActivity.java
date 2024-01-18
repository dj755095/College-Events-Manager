package com.example.eventinsti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton1;
    FirebaseDatabase database;
    DatabaseReference reference,reference2;

    Button adminButton, instituteButton, signupButton;
    TextView textView;
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

        signupButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                reference2 = database.getReference("Instiusers");

                String name = type;
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                String verifyStats = "Not Verified";

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                if(name.equals("i")){
                    HelperClass2 helperClass2 = new HelperClass2(username, verifyStats);
                    reference2.child(username).setValue(helperClass2);
                    Intent intent = new Intent(SignupActivity.this, SignupInstitute.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }else if(name.equals("s")){
                    Intent intent = new Intent(SignupActivity.this, SignupStudent.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
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
        instituteButton.setBackgroundResource(R.drawable.unselected_background);
        signupButton.setBackgroundResource(R.drawable.unselected_background);

        int color = ContextCompat.getColor(this, R.color.lavender);
        int defaultColor = ContextCompat.getColor(this,R.color.white);
//        adminButton.setTextColor(defaultColor);
//        instituteButton.setTextColor(defaultColor);
//        signupButton.setTextColor(defaultColor);

//        adminButton.setVisibility(View.VISIBLE);
        instituteButton.setVisibility(View.VISIBLE);
        signupButton.setVisibility(View.VISIBLE);

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
                instituteButton.setBackgroundResource(R.drawable.selected_background);
                //instituteButton.setTextColor(color);
                instituteButton.setVisibility(View.GONE);
                textView.setText("Institute Login");
                type = "i";
                break;
            case R.id.signupButton:
                signupButton.setBackgroundResource(R.drawable.selected_background);
                //signupButton.setTextColor(color);
                signupButton.setVisibility(View.GONE);
                textView.setText("Student Login");
                type = "s";
                break;
        }
    }


}
