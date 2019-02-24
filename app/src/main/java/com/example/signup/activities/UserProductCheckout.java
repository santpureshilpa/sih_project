package com.example.signup.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.signup.R;
import com.example.signup.adapters.UserProductCheckoutListAdapter;
import com.example.signup.models.Product;
import com.example.signup.models.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserProductCheckout extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    UserProductCheckoutListAdapter userProductCheckoutListAdapter;
    List<Product> productList = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_product_checkout);

        recyclerView = findViewById(R.id.rvProductCheckoutList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        userProductCheckoutListAdapter = new UserProductCheckoutListAdapter(this,productList);
        recyclerView.setAdapter(userProductCheckoutListAdapter);
        getProducts();
    }

    @Override
    public void onClick(View v) {

    }

    public void getProducts(){
        Shop shop = getIntent().getParcelableExtra("shop");
        if(shop != null){
            productList = shop.getProducts();
        }
        for(Product product : productList){
            userProductCheckoutListAdapter.notifyDataSetChanged();
        }
    }

}
