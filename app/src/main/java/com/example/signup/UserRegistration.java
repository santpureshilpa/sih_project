package com.example.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener{

    TextView newUserTxtView;
    EditText nameEditText,rationCardNoEditText,phoneNoEditText,cityEditText,passwordEditText;
    Button proceedBtn;
    CheckBox declarationChkBox,acceptChkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        //textView
        newUserTxtView = (TextView) findViewById(R.id.textViewWelcome);

        //editText
        nameEditText = (EditText) findViewById(R.id.editTextName);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        rationCardNoEditText = (EditText) findViewById(R.id.editTextRationNo);
        phoneNoEditText = (EditText) findViewById(R.id.editTextPhone);
        cityEditText = (EditText) findViewById(R.id.editTextCity);

        //buttons
        proceedBtn = (Button) findViewById(R.id.buttonProceed);

        //checkboxes
        declarationChkBox=(CheckBox)findViewById(R.id.checkBoxDeclaration);
        acceptChkBox=(CheckBox)findViewById(R.id.checkBoxAccept);

        //listeners
        proceedBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UserRegistration.this, OtpPage.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();

        //
        String name = nameEditText.getText().toString();
        String rationNo = rationCardNoEditText.getText().toString();
        String phone=phoneNoEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String password = passwordEditText.getText().toString();



    }
}