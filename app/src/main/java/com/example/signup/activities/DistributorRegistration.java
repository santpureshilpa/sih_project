package com.example.signup.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.models.Distributor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DistributorRegistration extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DistributorRegistration";

    EditText nameEditTxt,shopaddrEditTxt,licenseEditTxt,adharnoEditTxt,passwordEditTxt,mobilenoEditTxt;
    Button proceedBtn;
    CheckBox acceptChkBox;
    TextView WelcomeTextView;
    RadioButton governmentRadioBtn, privateRadioBtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_registration);
        //TextView
        WelcomeTextView=(TextView)findViewById(R.id.textViewWelcometext);

        //EditText
        passwordEditTxt=(EditText)findViewById(R.id.editTextPassword);
        nameEditTxt=(EditText)findViewById(R.id.editTextName);
        shopaddrEditTxt=(EditText)findViewById(R.id.editTextShopAddress);
        licenseEditTxt=(EditText)findViewById(R.id.editTextLicenseNo);
        adharnoEditTxt=(EditText)findViewById(R.id.editTextAdharNo);
        mobilenoEditTxt=(EditText)findViewById(R.id.editTextMobilNo);

        //Button
        proceedBtn=(Button)findViewById(R.id.buttonProceed);

        //Checkbox
        acceptChkBox=(CheckBox) findViewById(R.id.checkBoxAccept);

        //RadioButtons
        governmentRadioBtn=(RadioButton)findViewById(R.id.radioButtonGovernment);
        privateRadioBtn=(RadioButton)findViewById(R.id.radioButtonPrivate);

        //listeners
        proceedBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonProceed:
                registerDistributor();
                break;
        }
//        Intent intent = new Intent(DistributorRegistration.this, OtpPage.class);
//        startActivity(intent);
//        Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();
    }

    public void registerDistributor(){
        String name = nameEditTxt.getText().toString();
        String shopAddress = shopaddrEditTxt.getText().toString();
        String licenseNo= licenseEditTxt.getText().toString();
        int aadharNo = Integer.parseInt(adharnoEditTxt.getText().toString());
        int mobileNo= Integer.parseInt(mobilenoEditTxt.getText().toString());
        String password= passwordEditTxt.getText().toString();

        Distributor distributor = new Distributor();
        distributor.setName(name);
        distributor.setShopAddress(shopAddress);
        distributor.setShopLicenseNo(licenseNo);
        distributor.setAadharNo(aadharNo);
        distributor.setMobileNo(mobileNo);
        distributor.setPassword(password);

        db.collection("distributors").
                add(distributor).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "distributor added succesfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Get", "onFailure: ");
            }
        });
        Log.d("info", "button clicked");

    }
}
