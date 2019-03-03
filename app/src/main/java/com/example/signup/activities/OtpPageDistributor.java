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
import com.example.signup.models.Distributor;
import com.example.signup.models.Shop;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OtpPageDistributor extends AppCompatActivity implements View.OnClickListener{

    EditText editTextOtp;
    Distributor distributor;
    Shop shop;
    String sessionId;
    Button verfiyOtpBtn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "OtpPageDistributor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page_distributor);
        editTextOtp = findViewById(R.id.editTextOtp);
        distributor = getIntent().getParcelableExtra("distributor");
        shop = getIntent().getParcelableExtra("shop");
        verfiyOtpBtn = findViewById(R.id.buttonverify);
        verfiyOtpBtn.setOnClickListener(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        generateOTP(distributor.getMobileNo());
    }


    @Override
    public void onClick(View v) {
        verifyOTP();
    }

    public void generateOTP(long mobileNo){
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("https://2factor.in/API/V1/404fbc81-3d22-11e9-8806-0200cd936042/SMS/+91"+mobileNo+"/AUTOGEN");
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
                    Toast.makeText(OtpPageDistributor.this, "otp correct", Toast.LENGTH_LONG).show();
                    registerDistributor();
                } else {
                    Toast.makeText(OtpPageDistributor.this, "otp incorrect", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(OtpPageDistributor.this, "otp incorrect", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public void registerDistributor(){

        db.collection("distributors").
                add(distributor).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "distributor added succesfully");
                        shop.setDistributorId(documentReference.getId());
                        registerShopWithId(shop);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Get", "onFailure: ");
            }
        });
        Log.d("info", "button clicked");


    }

    public void registerShopWithId(Shop shop){
        db.collection("shops").
                add(shop).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Shop added successfully");
                        goToHomePage();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Get", "onFailure: ");
            }
        });
    }

    public void goToHomePage(){
        Intent intent=new Intent(OtpPageDistributor.this,DistributorHomePage.class);
        startActivity(intent);
    }

}
