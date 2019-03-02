package com.example.signup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.signup.R;

public class CustomerPassbook extends AppCompatActivity {

    EditText editTextRationCoins;
    TextView rationtext,welcometext;
    ImageView rationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_passbook);

    }
}
