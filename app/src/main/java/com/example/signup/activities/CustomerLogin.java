package com.example.signup.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.models.Customer;
import com.example.signup.models.Distributor;
import com.example.signup.utilities.AppPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CustomerLogin extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CustomerLogin";
    private static final String USER_TYPE = "customer";

    private TextView loginTextView, notregisteredTextView;
    private EditText phoneEditText, passwordEditText;
    private Button signinBtn, createnewBtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //textview
        loginTextView = (TextView) findViewById(R.id.textViewWelcomeText);
        notregisteredTextView = (TextView) findViewById(R.id.textViewNotRegistered);

        //EditText
        phoneEditText = (EditText) findViewById(R.id.editTextPhone);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);

        //Button
        createnewBtn = (Button) findViewById(R.id.buttonCreateNew);
        signinBtn = (Button) findViewById(R.id.buttonSignIn);

        //listener
        signinBtn.setOnClickListener(this);
        createnewBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
//        Intent intent = new Intent(UserLogin.this, UserRegistration.class);
//        startActivity(intent);

        switch (v.getId()) {
            case R.id.buttonSignIn:
                loginCustomer();
                break;
            case R.id.buttonCreateNew:
                goToUserReg();
        }

    }

    public void loginCustomer() {
        String phoneNo = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Log.d(TAG, "logging in customer");
        CollectionReference citiesRef = db.collection("customers");
        Query query = citiesRef
                .whereEqualTo("phoneNo", phoneNo)
                .whereEqualTo("password", password);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "logged in successfully");
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot queryDocSnapshot : querySnapshot) {

                        Customer customer = queryDocSnapshot.toObject(Customer.class);
                        customer.setId(queryDocSnapshot.getId());

                        AppPreference.setUserId(CustomerLogin.this,queryDocSnapshot.getId());
                        AppPreference.setUserType(CustomerLogin.this,USER_TYPE);


                        Log.d(TAG, customer.toString());
                        break;
                    }

                } else {
                    Log.d("MainActivity", "get failed with ", task.getException());
                }

                Intent intent=new Intent(CustomerLogin.this,HomePageCustomer.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "user not present please register");
            }
        });

    }

    public void goToUserReg(){
        Intent intent = new Intent(CustomerLogin.this, CustomerRegistration.class);
        startActivity(intent);
        // integrated successfully
    }
}


