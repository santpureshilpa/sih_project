package com.example.signup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.listeners.GotoNextActivity;
import com.example.signup.models.Product;


import java.util.ArrayList;
import java.util.List;

public class UserProductCheckoutListAdapter  extends RecyclerView.Adapter<UserProductCheckoutListAdapter.VIewHolder>  {

    Context context;
    List<Product> list = new ArrayList<>();


    public UserProductCheckoutListAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public UserProductCheckoutListAdapter.VIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_product_checkout_recycler_view,viewGroup,false);
        return new UserProductCheckoutListAdapter.VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductCheckoutListAdapter.VIewHolder vIewHolder, int i) {

        final Product product = list.get(i);
        if (product !=null){
            String productName = product.getName();
            int credit = product.getCredit();

            if (productName!=null){
                vIewHolder.shopName.setText(productName);
            }

            vIewHolder.shopAddr.setText(credit);

//            vIewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    gotoNextActivity.getValues(product);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        TextView shopName, shopAddr;
        LinearLayout linearLayout;
        public VIewHolder(@NonNull View itemView) {
            super(itemView);

            shopName = itemView.findViewById(R.id.shopName);
            shopAddr = itemView.findViewById(R.id.shopAddr);
            linearLayout = itemView.findViewById(R.id.llContainer);

        }
    }

}
