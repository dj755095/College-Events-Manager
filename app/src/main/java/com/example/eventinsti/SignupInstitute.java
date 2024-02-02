package com.example.eventinsti;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignupInstitute extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    Button uploadInstituteProfie;
    LinearLayout clgTypeLayout,degreeLayout,BranchLayout;
    private static final String[] sdegreeType = {"Select Degree: ","B.A", "B.com", "B.sc", "B.tech"};
    private static final String[] sbranchType = {"Select Branch: ","IT/CO", "Mechanical", "Electrical", "Civil","Production","Textile"};

    //String For checkbox concat
    String arts="";
    String commerce="";
    String science="";

    RadioGroup clgTypeRadio;
    RadioButton clgTypeRadioBtn;
    CheckBox checkbox_science,checkbox_commerce,checkbox_arts;
    CheckBox checkbox_BA, checkbox_BCOM, checkbox_BSC, checkbox_BTECH;
    String BA="",BCOM="",BSC="",BTECH="";
    CheckBox checkbox_IT,checkbox_ME,checkbox_EJ,checkbox_CE,checkbox_PRO,checkbox_TXT;
    String IT="",ME="",EJ="",CE="",PRO="",TXT="";
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

    Uri uri;
    String picURL="";
    String verifyStats="";

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
        checkbox_PRO = findViewById(R.id.checkbox_Production);
        checkbox_TXT = findViewById(R.id.checkbox_Textile);



        database = FirebaseDatabase.getInstance();


        uploadInstituteProfie = findViewById(R.id.uploadInstituteProfie);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                        }else{
                            Toast.makeText(SignupInstitute.this, "No PICTURE selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadInstituteProfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

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
                    CE = "CE ";
                }else {
                    CE ="";
                }
            }
        });checkbox_PRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_PRO.isChecked()){
                    PRO = "PRO ";
                }else {
                    PRO ="";
                }
            }
        });checkbox_TXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_TXT.isChecked()){
                    TXT = "TXT ";
                }else {
                    TXT ="";
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
            branchType= IT + ME + EJ + CE + PRO + TXT;
            //degreeType
            degreeType=BA+BCOM+BSC+BTECH;
            //For College Feilds
            String temp = science.concat(commerce);
            collegeFields = temp.concat(arts);

            Intent intent = getIntent();
            susername = intent.getStringExtra("username");
            verifyStats = intent.getStringExtra("verifyStats");
            profPicUploader();
            Toast.makeText(SignupInstitute.this, "Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SignupInstitute.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
    });

    }

    private void uploadData() {
        reference = database.getReference("Institute Data");
        HelperClassSignupInstitute hp = new HelperClassSignupInstitute( susername, instituteName, instituteCode, instituteAddress, institutePhone, collegeType, collegeFields, degreeType, branchType);
        reference.child(susername).setValue(hp);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }


    public void profPicUploader(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Institute pfp")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupInstitute.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                picURL = urlImage.toString();
                reference2 = database.getReference("Instiusers");
                HelperClass2 helperClass2 = new HelperClass2(susername, verifyStats, picURL);
                reference2.child(susername).setValue(helperClass2);
                uploadData();
                dialog.dismiss();
            }
        });

    }


}