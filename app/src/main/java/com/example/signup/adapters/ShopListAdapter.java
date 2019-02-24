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
import com.example.signup.models.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopListAdapter  extends RecyclerView.Adapter<ShopListAdapter.VIewHolder>  {

    Context context;
    List<Shop> list = new ArrayList<>();
    GotoNextActivity gotoNextActivity;

    public ShopListAdapter(Context context, List<Shop> list, GotoNextActivity gotoNextActivity) {
        this.context = context;
        this.list = list;
        this.gotoNextActivity = gotoNextActivity;
    }

    @NonNull
    @Override
    public ShopListAdapter.VIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_shop_list_recycleview,viewGroup,false);
        return new ShopListAdapter.VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListAdapter.VIewHolder vIewHolder, int i) {

        final Shop shop = list.get(i);
        if (shop!=null){
            String shopName = shop.getName();
            String shopAddr = shop.getAddress();

            if (shopName!=null){
                vIewHolder.shopName.setText(shopName);
            }
            if (shopAddr!=null){
                vIewHolder.shopAddr.setText(shopAddr);
            }

            vIewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoNextActivity.getValues(shop);
                }
            });
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
