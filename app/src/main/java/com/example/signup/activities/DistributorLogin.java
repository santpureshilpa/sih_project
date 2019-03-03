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
import com.example.signup.utilities.AppPreference;
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
    private static final String USER_TYPE = "distributor";

    private TextView notregisteredTextView, welcomeTextView;
    private EditText mobileNoEditTxt, passwordEditText;
    private Button signinBtn, createnewBtn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_login);

        //TextView
        welcomeTextView = (TextView) findViewById(R.id.textViewWelcomeText);
        notregisteredTextView = (TextView) findViewById(R.id.textViewNotRegistered);

        //EditText
        mobileNoEditTxt = (EditText) findViewById(R.id.editTextMobilNo);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);

        //Button
        signinBtn = (Button) findViewById(R.id.buttonSignIn);
        createnewBtn = (Button) findViewById(R.id.buttonCreateNew);

        //listener
        signinBtn.setOnClickListener(this);
        createnewBtn.setOnClickListener(this);

    }


    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonSignIn:
                loginDistributor();
                break;
            case R.id.buttonCreateNew:
                goToRegPage();
                break;

        }

    }

    public void loginDistributor(){
        int mobileNo = Integer.parseInt(mobileNoEditTxt.getText().toString());
        String password = passwordEditText.getText().toString();
        Log.d(TAG, "logging in distributor");
        CollectionReference distributorsRef = db.collection("distributors");
        Query query = distributorsRef
                        .whereEqualTo("mobileNo", mobileNo)
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

                        AppPreference.setUserId(DistributorLogin.this,queryDocSnapshot.getId());
                        AppPreference.setUserType(DistributorLogin.this,USER_TYPE);



                        Log.d(TAG, distributor.toString());
                        break;
                    }

                } else {
                    Log.d("MainActivity", "user not present please register ", task.getException());
                }

                Intent intent=new Intent(DistributorLogin.this,DistributorHomePage.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "user not present please register");
            }
        });
    }

    public void goToRegPage(){
        Intent intent = new Intent(DistributorLogin.this, DistributorRegistration.class);
        startActivity(intent);
    }


}