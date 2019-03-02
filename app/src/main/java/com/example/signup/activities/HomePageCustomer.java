package com.example.signup.activities;

import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.VisualVoicemailService;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.models.Customer;

public class HomePageCustomer extends AppCompatActivity {

    LinearLayout passbookLayout, mapLayout, homelayout, scanLayout, profileLayout, shopLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_customer);

        scanLayout = findViewById(R.id.scanid);
        shopLayout = findViewById(R.id.shopsid);
        passbookLayout = findViewById(R.id.passbookid);
        mapLayout = findViewById(R.id.mapid);
        homelayout = findViewById(R.id.myordersid);
        profileLayout = findViewById(R.id.profileid);

      passbookLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent passbook = new Intent(HomePageCustomer.this, CustomerPassbook.class);
                startActivity(passbook);
            }
        });

        mapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/maps/QAhtLnDnFvk"));
                startActivity(map);
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent (HomePageCustomer.this, CustomerProfile.class);
                startActivity(profile);
            }
        });

        shopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shop = new Intent (HomePageCustomer.this, SearchMain.class);
                startActivity(shop);
            }
        });

        scanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageCustomer.this, ScannedBarcodeActivity.class);
                startActivity(intent);
            }
        });


    }
}
