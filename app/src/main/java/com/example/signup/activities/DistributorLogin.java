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
import com.example.signup.models.Distributor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DistributorLogin extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DistributorLogin";

    private TextView notregisteredTextView, welcomeTextView;
    private EditText emailEditText, passwordEditText;
    private Button signinBtn, createnewBtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //TextView
        welcomeTextView = (TextView) findViewById(R.id.textViewWelcomeText);
        notregisteredTextView = (TextView) findViewById(R.id.textViewNotRegistered);

        //EditText
        emailEditText = (EditText) findViewById(R.id.editTextEmailId);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);

        //Button
        signinBtn = (Button) findViewById(R.id.buttonSignIn);
        createnewBtn = (Button) findViewById(R.id.buttonCreateNew);

        //listener
        signinBtn.setOnClickListener(this);
        createnewBtn.setOnClickListener(this);

    }


    public void onClick(View v) {
//        Intent intent = new Intent(DistributorLogin.this, DistributorRegistration.class);
//        startActivity(intent);
        switch (v.getId()){
            case R.id.buttonSignIn:
                loginDistributor();
                break;
        }

    }

    public void loginDistributor(){
        String emailId = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Log.d(TAG, "logging in distributor");
        CollectionReference citiesRef = db.collection("distributors");
        Query query = citiesRef
                        .whereEqualTo("emailId", emailId)
                        .whereEqualTo("password",password);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "logged in successfully");
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot queryDocSnapshot : querySnapshot) {
                        Distributor distributor = queryDocSnapshot.toObject(Distributor.class);
                        distributor.setId(queryDocSnapshot.getId());
                        Log.d(TAG, distributor.toString());
                        break;
                    }

                } else {
                    Log.d("MainActivity", "get failed with ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "user not present please register");
            }
        });
    }


}