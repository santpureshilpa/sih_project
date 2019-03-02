package com.example.signup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.signup.R;

public class CustomerProfile extends AppCompatActivity {
    TextView nameTextView,rationnotextView,cityTextView,phonenoTextView,emailidTextView,adharNoTextview,pannoTextView;
    ImageView profileImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_profile);

        //TextView
        nameTextView=(TextView)findViewById(R.id.textViewName);
        rationnotextView=(TextView)findViewById(R.id.textViewRationNo);
        cityTextView=(TextView)findViewById(R.id.textViewCity);
        phonenoTextView=(TextView)findViewById(R.id.textViewPhoneNo);
        emailidTextView=(TextView)findViewById(R.id.textViewEmailId);
        adharNoTextview=(TextView)findViewById(R.id.textViewAdharNo);
        pannoTextView=(TextView)findViewById(R.id.textViewPanNo);

        //IMAGE
        profileImageView=(ImageView) findViewById(R.id.imageViewProfile);

    }
}
