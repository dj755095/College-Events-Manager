package com.example.eventinsti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupInstitute extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    LinearLayout clgTypeLayout,degreeLayout,BranchLayout;
    private static final String[] sdegreeType = {"Select Degree: ","B.A", "B.com", "B.sc", "B.tech"};
    private static final String[] sbranchType = {"Select Branch: ","IT/CO", "Mechanical", "Electrical", "Civil"};

    //String For checkbox concat
    String arts="";
    String commerce="";
    String science="";

    RadioGroup clgTypeRadio;
    RadioButton clgTypeRadioBtn;
    CheckBox checkbox_science,checkbox_commerce,checkbox_arts;
    CheckBox checkbox_BA, checkbox_BCOM, checkbox_BSC, checkbox_BTECH;
    String BA="",BCOM="",BSC="",BTECH="";
    CheckBox checkbox_IT,checkbox_ME,checkbox_EJ,checkbox_CE;
    String IT="",ME="",EJ="",CE="";
    Button signupInstiSubmit;
    EditText inameI,icodeI,adressI,phoneI;

    String instituteName="";
    String instituteCode="";
    String instituteAddress="";
    String institutePhone="";
    String collegeType ="";
    String collegeFields = "Not Applicable";
    String degreeType="Not Applicable";
    String branchType="Not Applicable";
    String susername="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_institute);
        inameI = findViewById(R.id.inameI);
        icodeI = findViewById(R.id.icodeI);
        adressI = findViewById(R.id.adressI);
        phoneI = findViewById(R.id.phoneI);

        signupInstiSubmit = findViewById(R.id.signupInstiSubmit);
        //ChecKBoxes Inittialize
        checkbox_science = findViewById(R.id.checkbox_science);
        checkbox_commerce = findViewById(R.id.checkbox_commerce);
        checkbox_arts = findViewById(R.id.checkbox_arts);

        checkbox_BA = findViewById(R.id.checkbox_BA);
        checkbox_BCOM = findViewById(R.id.checkbox_BCOM);
        checkbox_BSC = findViewById(R.id.checkbox_BSC);
        checkbox_BTECH = findViewById(R.id.checkbox_BTECH);

        checkbox_IT = findViewById(R.id.checkbox_IT);
        checkbox_ME = findViewById(R.id.checkbox_ME);
        checkbox_EJ = findViewById(R.id.checkbox_EJ);
        checkbox_CE = findViewById(R.id.checkbox_CE);

        //Value settiing DegreeType
        checkbox_BA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_BA.isChecked()){
                    BA = "B.A ";
                }else {
                    BA ="";
                }
            }
        });
        checkbox_BCOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_BCOM.isChecked()){
                    BCOM = "B.Com ";
                }else {
                    BCOM ="";
                }
            }
        });
        checkbox_BSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_BSC.isChecked()){
                    BSC = "B.Sc ";
                }else {
                    BSC ="";
                }
            }
        });
        checkbox_BTECH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_BTECH.isChecked()){
                    BTECH = "B.Tech ";
                }else {
                    BTECH ="";
                }
            }
        });

        //Value Setting BranchType
        checkbox_IT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_IT.isChecked()){
                    IT = "IT ";
                }else {
                    IT ="";
                }
            }
        });
        checkbox_ME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_ME.isChecked()){
                    ME = "ME ";
                }else {
                    ME ="";
                }
            }
        });
        checkbox_EJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_EJ.isChecked()){
                    EJ = "EJ ";
                }else {
                    EJ ="";
                }
            }
        });
        checkbox_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_CE.isChecked()){
                    EJ = "EJ ";
                }else {
                    EJ ="";
                }
            }
        });


        //Value setting ARTS COMMERCE SCIENCE
        checkbox_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_science.isChecked()){
                    science = "Science ";
                }else {
                    science="";
                }
            }
        });
        checkbox_commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_commerce.isChecked()){
                    commerce = "Commerce ";
                }else {
                    commerce="";
                }
            }
        });
        checkbox_arts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_arts.isChecked()){
                    arts = "Arts ";
                }else {
                    arts="";
                }
            }
        });


        clgTypeLayout = findViewById(R.id.clgTypeLayout);
        clgTypeLayout.setVisibility(View.GONE);
        degreeLayout = findViewById(R.id.degreeLayout);
        degreeLayout.setVisibility(View.GONE);
        BranchLayout = findViewById(R.id.BranchLayout);
        BranchLayout.setVisibility(View.GONE);



        clgTypeRadio = findViewById(R.id.clgTypeRadio);
        
        clgTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioID = clgTypeRadio.getCheckedRadioButtonId();
                clgTypeRadioBtn = (RadioButton) findViewById(radioID);
                collegeType = clgTypeRadioBtn.getText().toString();
                if(collegeType.equals("Junior College")){
                    clgTypeLayout.setVisibility(View.VISIBLE);
                } else {
                    clgTypeLayout.setVisibility(View.GONE);
                }
                if (collegeType.equals("Degree College")) {
                    degreeLayout.setVisibility(View.VISIBLE);
                } else {
                    degreeLayout.setVisibility(View.GONE);
                }
                if (collegeType.equals("Engineering Degree College")|| collegeType.equals("Polytechnic College")) {
                    BranchLayout.setVisibility(View.VISIBLE);

                }else {
                    BranchLayout.setVisibility(View.GONE);
                }

            }
        });

    signupInstiSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            instituteName = inameI.getText().toString();
            instituteCode = icodeI.getText().toString();
            instituteAddress = adressI.getText().toString();
            institutePhone = phoneI.getText().toString();
            //branchType
            branchType= IT + ME + EJ + CE;
            //degreeType
            degreeType=BA+BCOM+BSC+BTECH;
            //For College Feilds
            String temp = science.concat(commerce);
            collegeFields = temp.concat(arts);

            Intent intent = getIntent();
            susername = intent.getStringExtra("username");
            uploadData();
            Intent i = new Intent(SignupInstitute.this,LoginActivity.class);
            startActivity(i);
        }
    });

    }

    private void uploadData() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Institute Data");
        HelperClassSignupInstitute hp = new HelperClassSignupInstitute( susername, instituteName, instituteCode, instituteAddress, institutePhone, collegeType, collegeFields, degreeType, branchType);
        reference.child(susername).setValue(hp);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }


}