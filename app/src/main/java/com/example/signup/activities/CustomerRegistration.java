package com.example.signup.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.models.Customer;
import com.example.signup.models.OTPSentResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomerRegistration extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "UserRegistration";

    TextView newUserTxtView;
    EditText nameEditText,rationCardNoEditText,phoneNoEditText,passwordEditText,adharnoEditText;
    EditText cityEditText;
    Button proceedBtn;
    CheckBox declarationChkBox,acceptChkBox;
    String sessionId;
    OTPSentResponse otpSentResponse;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        //textView
        newUserTxtView = (TextView) findViewById(R.id.textViewWelcome);

        //editText
        nameEditText = (EditText) findViewById(R.id.editTextName);
        passwordEditText = (EditText) findViewById(R.id.editTextCreatePassword);
        rationCardNoEditText = (EditText) findViewById(R.id.editTextRationNo);
        phoneNoEditText = (EditText) findViewById(R.id.editTextPhoneNo);
        adharnoEditText = (EditText) findViewById(R.id.editTextAdharNumb);
        cityEditText = (EditText) findViewById(R.id.editTextCity);


        //buttons
        proceedBtn = (Button) findViewById(R.id.buttonProceed);


        //checkboxes
        declarationChkBox=(CheckBox)findViewById(R.id.checkBoxDeclaration);
        acceptChkBox=(CheckBox)findViewById(R.id.checkBoxAccept);

        //listeners
        proceedBtn.setOnClickListener(this);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonProceed:
                jumpToOTPPage();
                break;
        }

    }


    public void jumpToOTPPage(){
        String name = nameEditText.getText().toString();
        String rationNo = rationCardNoEditText.getText().toString();
        long phoneNo=Long.parseLong(phoneNoEditText.getText().toString());
        String password = passwordEditText.getText().toString();
        String city = cityEditText.getText().toString();


        Customer customer = new Customer();
        customer.setName(name);
        customer.setRationNo(rationNo);
        customer.setPhoneNo(phoneNo);
        customer.setCity(city);
        customer.setPassword(password);


        Intent intent=new Intent(CustomerRegistration.this,OtpPageCustomer.class);
        intent.putExtra("customer", customer);
        startActivity(intent);
    }




}
