package com.example.signup.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class GoToPayment extends AppCompatActivity {

    private Button button;
    private TextView distributor,ration_coins,distributor_text,ration_coin_text;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_payment);

        this.setTitle("Credit Transfer");


        button=(Button)findViewById(R.id.button);
        distributor_text=(TextView)findViewById(R.id.distributor_textid);
        ration_coin_text=(TextView)findViewById(R.id.ration_coins_textid);
        final String clickedDis = AppPreference.getClickedDistributor(GoToPayment.this);
        final String totalAmount = AppPreference.getTotalPayableAmountFromCusToDes(GoToPayment.this);
        distributor_text.setText("dwfe");
        ration_coin_text.setText(totalAmount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),totalAmount + " Ration Coins Successfully Transferd to " + "dwfe",Toast.LENGTH_LONG).show();
                String custId = AppPreference.getUserId(GoToPayment.this);
                getCustomer(custId, 200);
                getDistributor(clickedDis, 200);
            }
        });


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
