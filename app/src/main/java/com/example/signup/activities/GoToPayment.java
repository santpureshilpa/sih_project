package com.example.signup.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.models.Customer;
import com.example.signup.models.Distributor;
import com.example.signup.utilities.AppPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoToPayment extends AppCompatActivity {

    private Button button,button1;
    private TextView distributor,ration_coins,distributor_text,ration_coin_text;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String sessionId;
    private EditText editTextOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_payment);

        this.setTitle("Credit Transfer");

        editTextOTP = findViewById(R.id.editTextOTP);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);

        distributor_text=(TextView)findViewById(R.id.distributor_textid);
        ration_coin_text=(TextView)findViewById(R.id.ration_coins_textid);
        final String clickedDis = AppPreference.getClickedDistributor(GoToPayment.this);
        final String totalAmount = AppPreference.getTotalPayableAmountFromCusToDes(GoToPayment.this);
        distributor_text.setText("A.P.Kale");
        ration_coin_text.setText(totalAmount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),totalAmount + " Ration Coins Successfully Transferd to " + "dwfe",Toast.LENGTH_LONG).show();
                verifyOTP();

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rate= new Intent (GoToPayment.this,rating.class);
                startActivity(rate);
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        long mobileNo = Long.parseLong(AppPreference.getCustomerMobileNo(GoToPayment.this));
        generateOTP(mobileNo);

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
        String otpEntered = editTextOTP.getText().toString();
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
                    //Toast.makeText(CustomerRegistration.this, "otp correct", Toast.LENGTH_LONG).show();
                    String custId = AppPreference.getUserId(GoToPayment.this);
                    String clickedDis = AppPreference.getClickedDistributor(GoToPayment.this);
                    final String totalAmount = AppPreference.getTotalPayableAmountFromCusToDes(GoToPayment.this);
                    int creditFinal = new Double(Double.parseDouble(totalAmount)).intValue();
                    getCustomer(custId, creditFinal);



                } else {
                    Toast.makeText(GoToPayment.this, "otp incorrect", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(GoToPayment.this, "otp incorrect", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public void getCustomer(final String customerId, final int amountTODeduct){
        DocumentReference docRef = db.collection("customers").document(customerId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("MainActivity", "DocumentSnapshot data: " + document.getData());
                        Customer customer = document.toObject(Customer.class);
                        customer.setCredit(customer.getCredit() - amountTODeduct);
                        updateCustomer(customer, customerId);
                    } else {
                        Log.d("MainActivity", "No such document");
                    }
                } else {
                    Log.d("MainActivity", "get failed with ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("MainActivity", "Error adding document", e);
            }
        });
    }

    public void updateCustomer(Customer customer, String cusId){
        db.collection("customers").document(cusId)
                .set(customer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "DocumentSnapshot successfully updated!");
                        String clickedDis = AppPreference.getClickedDistributor(GoToPayment.this);
                        final String totalAmount = AppPreference.getTotalPayableAmountFromCusToDes(GoToPayment.this);
                        int creditFinal = new Double(Double.parseDouble(totalAmount)).intValue();
                        getDistributor(clickedDis, creditFinal);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating document", e);
                    }
                });
    }

    public void getDistributor(final String distributorId, final int amountTOAdd){
        DocumentReference docRef = db.collection("distributors").document(distributorId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("MainActivity", "DocumentSnapshot data: " + document.getData());
                        final Distributor distributor = document.toObject(Distributor.class);
                        distributor.setCredit(distributor.getCredit() + amountTOAdd);
                        updateDistributor(distributor, distributorId);
                    } else {
                        Log.d("MainActivity", "No such document");
                    }
                } else {
                    Log.d("MainActivity", "get failed with ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("MainActivity", "Error adding document", e);
            }
        });
    }

    public void updateDistributor(Distributor distributor, String distId){
        db.collection("distributors").document(distId)
                .set(distributor)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "DocumentSnapshot successfully updated!");
                        
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating document", e);
                    }
                });
    }
}
