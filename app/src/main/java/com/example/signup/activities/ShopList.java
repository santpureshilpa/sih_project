package com.example.signup.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.signup.R;
import com.example.signup.adapters.ShopListAdapter;
import com.example.signup.listeners.GotoNextActivity;
import com.example.signup.models.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShopList extends AppCompatActivity implements View.OnClickListener, GotoNextActivity {

    RecyclerView recyclerView;
    ShopListAdapter shopListAdapter;
    List<Shop> shopList = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        getShopsList();
        recyclerView = findViewById(R.id.rvShopList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        shopListAdapter = new ShopListAdapter(this,shopList,this);
        recyclerView.setAdapter(shopListAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    public void getShopsList(){
        db.collection("shops").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("MainActivity", document.getId() + " => " + document.getData());
                        Shop shop = document.toObject(Shop.class);
                        shopList.add(shop);
                        shopListAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d("MainActivity", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    @Override
    public void getValues(Shop shop) {

        Intent intent = new Intent(this,UserProductCheckout.class);
        intent.putExtra("shop",shop);
        startActivity(intent);
    }
}
