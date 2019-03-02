package com.example.signup.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.signup.R;

public class DistributorHomePage extends AppCompatActivity {


        LinearLayout qrcodelayout, govttransferlayout, passbooklayout, myprofilelayout, itemslayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.distributor_home_page);

            myprofilelayout = (LinearLayout) findViewById(R.id.profileid);
            passbooklayout = (LinearLayout) findViewById(R.id.passbookid);
            itemslayout = (LinearLayout) findViewById(R.id.itemsid);
            //qrcodelayout = (LinearLayout) findViewById(R.id.qrcodeid);
            govttransferlayout = (LinearLayout) findViewById(R.id.govtaccid);
           myprofilelayout = (LinearLayout) findViewById(R.id.profileid);


            myprofilelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent map = new Intent(DistributorHomePage.this,DistributorProfile.class);
                    startActivity(map);
                }
            });

            passbooklayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent passbook = new Intent(DistributorHomePage.this, DistributorPassbook.class);
                    startActivity(passbook);
                }
            });

            itemslayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent items = new Intent(DistributorHomePage.this, ItemsList.class);
                    startActivity(items);
                }
            });

            govttransferlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent transfer = new Intent(DistributorHomePage.this, GovermentTransfer.class);
                    startActivity(transfer);
                }
            });
           /* qrcodelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent qr = new Intent(DistributorHomePage.this, DistributorQRCodeGenerator.class);
                    startActivity(qr);
                }
            });*/
        }
    }
