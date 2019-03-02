package com.example.signup.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.models.Customer;
import com.example.signup.models.Distributor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerProfile extends AppCompatActivity {
    TextView nameTextView,rationnotextView,cityTextView,phonenoTextView,emailidTextView,adharNoTextview,pannoTextView;
    ImageView profileImageView;
    FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_profile);

        //TextView
        nameTextView=(TextView)findViewById(R.id.textViewName);
        rationnotextView=(TextView)findViewById(R.id.textViewRationNo);
        cityTextView=(TextView)findViewById(R.id.textViewCity);
        phonenoTextView=(TextView)findViewById(R.id.textViewPhoneNo);
       emailidTextView=(TextView)findViewById(R.id.textViewEmailId);
        adharNoTextview=(TextView)findViewById(R.id.textViewAdharNo);
       pannoTextView=(TextView)findViewById(R.id.textViewPanNo);

        //IMAGE
        profileImageView=(ImageView) findViewById(R.id.imageViewProfile);
        getcustomer();

    }

    private void getcustomer() {
        DocumentReference docRef = db.collection("customers").document(
                "17j2Zgghpb7D3ImokVM1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("CustomerProfile", "DocumentSnapshot data: " + document.getData());
                        Customer customer = document.toObject(Customer.class);
                        nameTextView.setText(String.valueOf(customer.getName()));
                        rationnotextView.setText(String.valueOf(customer.getRationNo()));
                        cityTextView.setText(String.valueOf(customer.getCity()));
                        phonenoTextView.setText(String.valueOf(customer.getPhoneNo()));
                        emailidTextView.setText(String.valueOf(customer.getEmailId()));
                        adharNoTextview.setText(String.valueOf(customer.getAadharNo()));
                        pannoTextView.setText(String.valueOf(customer.getPanNo()));

                        String userString = customer.toString();
                        Log.d("CustomerProfile", "DocumentSnapshot data: " + userString);

                    } else {
                        Log.d("CustomerProfile", "No such document");
                    }
                } else {
                    Log.d("CustomerProfile", "get failed with ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("CustomerProfile", "Error adding document", e);
            }
        });
    }


}

