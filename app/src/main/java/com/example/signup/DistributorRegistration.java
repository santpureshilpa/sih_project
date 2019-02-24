package com.example.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class DistributorRegistration extends AppCompatActivity implements View.OnClickListener{

    EditText nameEditTxt,shopaddrEditTxt,licenseEditTxt,adharnoEditTxt,passwordEditTxt,mobilenoEditTxt;
    Button proceedBtn;
    CheckBox acceptChkBox;
    TextView WelcomeTextView;
    RadioButton governmentRadioBtn, privateRadioBtn;

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
        Intent intent = new Intent(DistributorRegistration.this, OtpPage.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();

        //
        String name = nameEditTxt.getText().toString();
        String shopAddress = shopaddrEditTxt.getText().toString();
        String licenseNo= licenseEditTxt.getText().toString();
        String adharNo = adharnoEditTxt.getText().toString();
        String mobileNo= mobilenoEditTxt.getText().toString();
        String password= passwordEditTxt.getText().toString();



    }
}

