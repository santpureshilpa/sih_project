package com.example.signup.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.utilities.AppPreference;

public class OtpPage extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "OtpPage";

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
        String user_id = AppPreference.getUserId(OtpPage.this);
        String userType = AppPreference.getUserType(OtpPage.this);;
        Log.d(TAG, userType + " " + user_id);
        verify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        

    }
}
