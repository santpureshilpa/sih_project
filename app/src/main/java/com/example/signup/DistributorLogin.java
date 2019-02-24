package com.example.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DistributorLogin extends AppCompatActivity implements View.OnClickListener {

    private TextView notregisteredTextView, welcomeTextView;
    private EditText mobilenoEditText, passwordEditText;
    private Button signinBtn, createnewBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //TextView
        welcomeTextView = (TextView) findViewById(R.id.textViewWelcomeText);
        notregisteredTextView = (TextView) findViewById(R.id.textViewNotRegistered);

        //EditText
        mobilenoEditText = (EditText) findViewById(R.id.editTextMobilNo);
        passwordEditText = (EditText) findViewById(R.id.editTextShopAddress);

        //Button
        signinBtn = (Button) findViewById(R.id.buttonSignIn);
        createnewBtn = (Button) findViewById(R.id.buttonCreateNew);

        //listener
        signinBtn.setOnClickListener(this);
        createnewBtn.setOnClickListener(this);

    }


    public void onClick(View v) {
        Intent intent = new Intent(DistributorLogin.this, DistributorRegistration.class);
        startActivity(intent);

        String mobileNo = mobilenoEditText.getText().toString();
        String password = passwordEditText.getText().toString();
    }

}