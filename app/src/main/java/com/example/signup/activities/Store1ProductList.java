package com.example.signup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.utilities.AppPreference;

public class Store1ProductList extends AppCompatActivity {

    private TextView Product1;
    private TextView Product2;
    private TextView Product3;
    private TextView SumOfProducts;
    private Button TotalProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store1productlist);
        final Button Total = (Button)findViewById(R.id.Total1);
        final TextView Ration_Coin = (TextView) findViewById(R.id.RationCoinId);
        final EditText Quantity = (EditText)findViewById(R.id.QuantityId);
        final TextView Sum = (TextView)findViewById(R.id.SumId);



        final Button Total2 = (Button)findViewById(R.id.Total2);
        final TextView Ration_Coin2 = (TextView) findViewById(R.id.RationCoin2Id);
        final EditText Quantity2 = (EditText)findViewById(R.id.Quantity2Id);
        final TextView Sum2 = (TextView)findViewById(R.id.Sum2Id);

        final Button Total3 = (Button)findViewById(R.id.Total3);
        final TextView Ration_Coin3 = (TextView) findViewById(R.id.RationCoin3Id);
        final EditText Quantity3 = (EditText)findViewById(R.id.Quantity3Id);
        final TextView Sum3 = (TextView)findViewById(R.id.Sum3Id);


       Total.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Sum.setText(String.valueOf(Double.valueOf(String.valueOf(Ration_Coin.getText()))*Double.valueOf(String.valueOf(Quantity.getText()))));

           }
       });


       Total2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Sum2.setText(String.valueOf(Double.valueOf(String.valueOf(Ration_Coin2.getText()))*Double.valueOf(String.valueOf(Quantity2.getText()))));
           }
       });

       Total3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Sum3.setText(String.valueOf(Double.valueOf(String.valueOf(Ration_Coin3.getText()))*Double.valueOf(String.valueOf(Quantity3.getText()))));

           }
       });


        Product1 = (TextView) findViewById(R.id.SumId);
        Product2 = (TextView) findViewById(R.id.Sum2Id);
        Product3 = (TextView) findViewById(R.id.Sum3Id);
        SumOfProducts = (TextView)findViewById(R.id.Sum5Id);
        TotalProducts = (Button) findViewById(R.id.Total5);


        TotalProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double num1 = Double.parseDouble(Product1.getText().toString());
                double num2 = Double.parseDouble(Product2.getText().toString());
                double num3 = Double.parseDouble(Product3.getText().toString());

                double sum = num1+num2+num3;
                SumOfProducts.setText(String.valueOf(sum));
                AppPreference.setTotalPayableAmountFromCusToDes(Store1ProductList.this, String.valueOf(sum));
                Intent intent = new Intent(Store1ProductList.this, GoToPayment.class);
                startActivity(intent);
            }
        });







    }

    }

