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
    EditText nameEditText,rationCardNoEditText,phoneNoEditText,passwordEditText,adharnoEditText, otpEditText;
    EditText cityEditText;
    Button proceedBtn, generateOTPBtn;
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
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        rationCardNoEditText = (EditText) findViewById(R.id.editTextRationNo);
        phoneNoEditText = (EditText) findViewById(R.id.editTextPhone);
        adharnoEditText = (EditText) findViewById(R.id.editTextAdharNumb);
        cityEditText = (EditText) findViewById(R.id.editTextCity);
        otpEditText = findViewById(R.id.otpEditText);

        //buttons
        proceedBtn = (Button) findViewById(R.id.buttonProceed);
        generateOTPBtn = findViewById(R.id.generateOTPBtn);

        //checkboxes
        declarationChkBox=(CheckBox)findViewById(R.id.checkBoxDeclaration);
        acceptChkBox=(CheckBox)findViewById(R.id.checkBoxAccept);

        //listeners
        proceedBtn.setOnClickListener(this);
        generateOTPBtn.setOnClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonProceed:
                verifyOTP();
                    //registerCustomer();
                break;
            case R.id.generateOTPBtn:
                generateOTP();
                break;
        }
//        Intent intent = new Intent(UserRegistration.this, OtpPage.class);
//        startActivity(intent);
//        Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();



    }

    public void generateOTP(){
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("https://2factor.in/API/V1/404fbc81-3d22-11e9-8806-0200cd936042/SMS/+919623580938/AUTOGEN");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                sessionId = jsonObject.getString("Details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public void verifyOTP() {
        String otpEntered = otpEditText.getText().toString();
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("https://2factor.in/API/V1/404fbc81-3d22-11e9-8806-0200cd936042/SMS/VERIFY/" + sessionId + "/" + otpEntered);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                sessionId = jsonObject.getString("Details");
                String status = jsonObject.getString("Status");
                if (status.equals("Success")) {
                    Toast.makeText(CustomerRegistration.this, "otp correct", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CustomerRegistration.this, "otp incorrect", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(CustomerRegistration.this, "otp incorrect", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public void registerCustomer(){
        String name = nameEditText.getText().toString();
        String rationNo = rationCardNoEditText.getText().toString();
        int phoneNo=Integer.parseInt(phoneNoEditText.getText().toString());
        String password = passwordEditText.getText().toString();
        String city = cityEditText.getText().toString();


        Customer customer = new Customer();
        customer.setName(name);
        customer.setRationNo(rationNo);
        customer.setPhoneNo(phoneNo);
        customer.setCity(city);
        customer.setPassword(password);


        Intent intent=new Intent(CustomerRegistration.this,HomePageCustomer.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Account Created Successfully", Toast.LENGTH_LONG).show();

        db.collection("customers").
                add(customer).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "distributor added succesfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Get", "onFailure: ");
            }
        });
        Log.d("info", "button clicked");


    }




}
