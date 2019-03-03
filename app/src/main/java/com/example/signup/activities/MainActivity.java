package com.example.signup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.utilities.AppPreference;

public class MainActivity extends AppCompatActivity {
    private TextView  rationhubtext;
    private TextView  loginastxt;
    private Button User;
    private Button dist;
    private Button admin;
    //private Image userimg;
    //private Image distimg;
    //private Image imageView3 ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rationhubtext=(TextView)findViewById(R.id.textView);
        loginastxt=(TextView)findViewById(R.id.textViewWelcomeText);
        User=(Button)findViewById(R.id.User);
        dist=(Button)findViewById(R.id.dist);
        admin=(Button)findViewById(R.id.admin);
        // userimg=(Image)findViewById(R.id.userimg);
        // distimg=(Image)findViewById(R.id.distimg);

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this, CustomerLogin.class);
                startActivity(intent1);
            }
        });

        dist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this, DistributorLogin.class);
                startActivity(intent2);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(MainActivity.this, adminlogin.class);
                startActivity(intent3);


            }
        });



    }
}
