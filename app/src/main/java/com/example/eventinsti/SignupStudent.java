package com.example.eventinsti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupStudent extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    private Spinner classSpinner,branchSpinner,yearSpinner, gradSpinner;
    TextView classTextview,branchTextview,yearTextview, gradTextview;
    EditText nameS,inameS,icodeS,phoneS;
    RadioGroup genderRadio,classRadio;
    Button submit;
    RadioButton genderBtn, classBtn;
    private static final String[] sclass = {"Select class: ","Science", "Commerce", "Arts", "Graduation Degree", "Diploma"};
    private static final String[] sbranch = {"Select Branch: ","IT/CO", "Mechanical", "Electrical", "Civil"};
    private static final String[] syear = {"Select Year: ","1st", "2nd", "3rd", "4th"};
    private static final String[] sgrad = {"Select Degree: ","B.A", "B.com", "B.sc", "B.tech"};

    String studyClass = "";
    String studyBranch = "";
    String studyYear = "";
    String studyGrad = "";

    String susername="";
    String name="";
    String instituteName="";
    String instituteCode="";
    String phoneNumber="";
    String gender="";
    String juniorStudyClass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);
        nameS = findViewById(R.id.nameS);
        inameS = findViewById(R.id.inameS);
        icodeS = findViewById(R.id.icodeS);
        phoneS = findViewById(R.id.phoneS);

        submit = findViewById(R.id.signupStudSubmit);


        genderRadio = findViewById(R.id.genderRadio);

        classRadio = findViewById(R.id.classRadio);
        classTextview = findViewById(R.id.classTextview);
        classRadio.setVisibility(View.GONE);
        classTextview.setVisibility(View.GONE);

        branchSpinner = findViewById(R.id.branchSpinner);
        branchTextview = findViewById(R.id.branchTextview);
        branchSpinner.setVisibility(View.GONE);
        branchTextview.setVisibility(View.GONE);
        branchSpinnerMethod();

        yearSpinner = findViewById(R.id.yearSpinner);
        yearTextview = findViewById(R.id.yearTextview);
        yearSpinner.setVisibility(View.GONE);
        yearTextview.setVisibility(View.GONE);
        yearSpinnerMethod();

        gradSpinner = findViewById(R.id.gradSpinner);
        gradTextview = findViewById(R.id.gradTextview);
        gradSpinner.setVisibility(View.GONE);
        gradTextview.setVisibility(View.GONE);
        gradSpinnerMethod();

        classSpinner = findViewById(R.id.classSpinner);
        classSpinnerMethod();

        //Submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int genderID = genderRadio.getCheckedRadioButtonId();
                genderBtn = (RadioButton) findViewById(genderID);
                gender = genderBtn.getText().toString();

                int classID = classRadio.getCheckedRadioButtonId();
                classBtn = (RadioButton) findViewById(classID);
                juniorStudyClass = classBtn.getText().toString();

                name =nameS.getText().toString();
                instituteName =inameS.getText().toString();
                instituteCode =icodeS.getText().toString();
                phoneNumber =phoneS.getText().toString();

                Intent intent = getIntent();
                susername = intent.getStringExtra("username");
                uploadData();
                Intent i = new Intent(SignupStudent.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }

    private void uploadData() {
        //Todo Code to upload data to Firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Student Data");

        HelperClassSignupStudent hp = new HelperClassSignupStudent(susername, name, instituteName, instituteCode, phoneNumber, gender, studyClass, juniorStudyClass,studyBranch, studyYear, studyGrad);
        reference.child(susername).setValue(hp);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    private void gradSpinnerMethod() {
        ArrayAdapter<String> adapterGrad = new ArrayAdapter<String>(SignupStudent.this, android.R.layout.simple_spinner_item,sgrad);
        adapterGrad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradSpinner.setAdapter(adapterGrad);
        gradSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studyGrad = (String) parent.getItemAtPosition(position);
                Toast.makeText(SignupStudent.this, "Year :  "+studyGrad, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void yearSpinnerMethod() {
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(SignupStudent.this, android.R.layout.simple_spinner_item,syear);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapterYear);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studyYear = (String) parent.getItemAtPosition(position);
                Toast.makeText(SignupStudent.this, "Year :  "+studyYear, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void branchSpinnerMethod() {
        ArrayAdapter<String> adapterBranch = new ArrayAdapter<String>(SignupStudent.this, android.R.layout.simple_spinner_item,sbranch);
        adapterBranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(adapterBranch);
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studyBranch = (String) parent.getItemAtPosition(position);
                Toast.makeText(SignupStudent.this, "Branch "+studyBranch, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void classSpinnerMethod() {
        ArrayAdapter<String> adapterStudy = new ArrayAdapter<String>(SignupStudent.this, android.R.layout.simple_spinner_item,sclass);
        adapterStudy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapterStudy);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                studyClass= (String) parent.getItemAtPosition(position);
                Toast.makeText(SignupStudent.this, "Class "+studyClass, Toast.LENGTH_SHORT).show();
                if(studyClass.equals("Arts")||studyClass.equals("Commerce")||studyClass.equals("Science")){
                    classRadio.setVisibility(View.VISIBLE);
                    classTextview.setVisibility(View.VISIBLE);
                }else{
                    classRadio.setVisibility(View.GONE);
                    classTextview.setVisibility(View.GONE);
                }
                if(studyClass.equals("Diploma")){
                    branchSpinner.setVisibility(View.VISIBLE);
                    branchTextview.setVisibility(View.VISIBLE);
                    yearSpinner.setVisibility(View.VISIBLE);
                    yearTextview.setVisibility(View.VISIBLE);
                }else{
                    branchSpinner.setVisibility(View.GONE);
                    branchTextview.setVisibility(View.GONE);
                    yearSpinner.setVisibility(View.GONE);
                    yearTextview.setVisibility(View.GONE);
                }

                if(studyClass.equals("Graduation Degree")){
                    gradSpinner.setVisibility(View.VISIBLE);
                    gradTextview.setVisibility(View.VISIBLE);
                }else{
                    gradSpinner.setVisibility(View.GONE);
                    gradTextview.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


}