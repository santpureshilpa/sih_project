package com.example.signup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.signup.R;

public class Store1ProductList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store1productlist);
        final Button Total = (Button)findViewById(R.id.Total);
        final EditText Ration_Coin = (EditText)findViewById(R.id.RationCoinId);
        final EditText Quantity = (EditText)findViewById(R.id.QuantityId);
        final TextView Sum = (TextView)findViewById(R.id.SumId);
       Total.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Sum.setText(String.valueOf(Double.valueOf(String.valueOf(Ration_Coin.getText()))*Double.valueOf(String.valueOf(Quantity.getText()))));

           }
       });




        }

    }

