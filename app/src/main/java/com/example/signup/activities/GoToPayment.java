package com.example.signup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.R;

public class GoToPayment extends AppCompatActivity {

    private Button button;
    private TextView distributor,ration_coins,distributor_text,ration_coin_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_payment);

        this.setTitle("Credit Transfer");


        button=(Button)findViewById(R.id.button);
        distributor_text=(TextView)findViewById(R.id.distributor_textid);
        ration_coin_text=(TextView)findViewById(R.id.ration_coins_textid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"112 Ration Coins Successfully Transferd to A.P.Kale",Toast.LENGTH_LONG).show();
            }
        });

    }
}
