package com.example.signup.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.R;
import com.example.signup.models.Customer;
import com.example.signup.models.Distributor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerRegistration extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "UserRegistration";

    TextView newUserTxtView;
    EditText nameEditText,rationCardNoEditText,phoneNoEditText,cityEditText,passwordEditText;
    Button proceedBtn;
    CheckBox declarationChkBox,acceptChkBox;

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        //textView
        newUserTxtView = (TextView) findViewById(R.id.textViewWelcome);

        //editText
        nameEditText = (EditText) findViewById(R.id.editTextName);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        rationCardNoEditText = (EditText) findViewById(R.id.editTextRationNo);
        phoneNoEditText = (EditText) findViewById(R.id.editTextPhone);
        cityEditText = (EditText) findViewById(R.id.editTextCity);

        //buttons
        proceedBtn = (Button) findViewById(R.id.buttonProceed);

        //checkboxes
        declarationChkBox=(CheckBox)findViewById(R.id.checkBoxDeclaration);
        acceptChkBox=(CheckBox)findViewById(R.id.checkBoxAccept);

        //listeners
        proceedBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonProceed:
                registerCustomer();
                break;
        }
//        Intent intent = new Intent(UserRegistration.this, OtpPage.class);
//        startActivity(intent);
//        Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();



    }
    public void registerCustomer(){
        String name = nameEditText.getText().toString();
        String rationNo = rationCardNoEditText.getText().toString();
        int phoneNo=Integer.parseInt(phoneNoEditText.getText().toString());
        String city = cityEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setRationNo(rationNo);
        customer.setPhoneNo(phoneNo);
        customer.setCity(city);
        customer.setPassword(password);


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
