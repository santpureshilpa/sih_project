package com.example.signup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.models.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OtpPageCustomer extends AppCompatActivity implements View.OnClickListener{

    EditText editTextOtp;
    Customer customer;
    String sessionId;
    Button otpVerifyBtn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "OtpPageCustomer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page_customer);
        editTextOtp = findViewById(R.id.editTextOtp);
        otpVerifyBtn = findViewById(R.id.buttonverify);
        otpVerifyBtn.setOnClickListener(this);
        customer = getIntent().getParcelableExtra("customer");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        generateOTP(customer.getPhoneNo());
    }


    @Override
    public void onClick(View v) {
        verifyOTP();
    }

    public void generateOTP(long phoneNo){
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("https://2factor.in/API/V1/404fbc81-3d22-11e9-8806-0200cd936042/SMS/+91"+phoneNo+"/AUTOGEN");
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
        String otpEntered = editTextOtp.getText().toString();
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
                    Toast.makeText(OtpPageCustomer.this, "otp correct", Toast.LENGTH_LONG).show();
                    registerCustomer();
                } else {
                    Toast.makeText(OtpPageCustomer.this, "otp incorrect", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(OtpPageCustomer.this, "otp incorrect", Toast.LENGTH_LONG).show();
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

        db.collection("customers").
                add(customer).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "distributor added succesfully");
                        goToHomePage();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Get", "onFailure: ");
            }
        });
        Log.d("info", "button clicked");


    }

    public void goToHomePage(){
        Intent intent=new Intent(OtpPageCustomer.this,HomePageCustomer.class);
        startActivity(intent);
    }
}
