package com.example.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtpPage extends AppCompatActivity implements View.OnClickListener{

    TextView OTPtext;
    TextView OTPtext2;
    EditText OTPno;
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        OTPtext=(TextView)findViewById(R.id.textViewWelcomeOtpText);
        OTPtext2=(TextView)findViewById(R.id.textViewOtpText);
        OTPno=(EditText)findViewById(R.id.editTextEnterOtp);
        verify=(Button)findViewById(R.id.buttonVerify);


        verify.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        

    }
}
