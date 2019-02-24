package com.example.signup.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Transaction implements Parcelable {
    String id;
    String customerId;
    int totalCreditSpent;
    Date date;
    List<Product> productList;
    String shopId;
    String feedback;

    public Transaction() {
    }

    protected Transaction(Parcel in) {
        id = in.readString();
        customerId = in.readString();
        totalCreditSpent = in.readInt();
        productList = in.createTypedArrayList(Product.CREATOR);
        shopId = in.readString();
        feedback = in.readString();

    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getTotalCreditSpent() {
        return totalCreditSpent;
    }

    public void setTotalCreditSpent(int totalCreditSpent) {
        this.totalCreditSpent = totalCreditSpent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Transaction(String id, String customerId, int totalCreditSpent, Date date, List<Product> productList, String shopId, String feedback) {
        this.id = id;
        this.customerId = customerId;
        this.totalCreditSpent = totalCreditSpent;
        this.date = date;
        this.productList = productList;
        this.shopId = shopId;
        this.feedback = feedback;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(customerId);
        dest.writeInt(totalCreditSpent);
        dest.writeList(productList);
        dest.writeString(shopId);
        dest.writeString(feedback);
    }
}
