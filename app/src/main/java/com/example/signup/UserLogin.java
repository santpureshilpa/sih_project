package com.example.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    private TextView loginTextView, notregisteredTextView;
    private EditText mobilenoEditText, passwordEditText;
    private Button signinBtn, createnewBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //textview
        loginTextView = (TextView) findViewById(R.id.textViewWelcomeText);
        notregisteredTextView = (TextView) findViewById(R.id.textViewNotRegistered);

        //EditText
        mobilenoEditText = (EditText) findViewById(R.id.editTextMobilNo);
        passwordEditText = (EditText) findViewById(R.id.editTextShopAddress);

        //Button
        createnewBtn = (Button) findViewById(R.id.buttonCreateNew);
        signinBtn = (Button) findViewById(R.id.buttonSignIn);

        //listener
        signinBtn.setOnClickListener(this);
        createnewBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(UserLogin.this, UserRegistration.class);
        startActivity(intent);

        String mobileNo = mobilenoEditText.getText().toString();
        String password = passwordEditText.getText().toString();
    }

}


