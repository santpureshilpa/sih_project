package com.example.signup.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    String id;
    String name;
    int credit;

    public Product() {
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        credit = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Product(String id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(credit);
    }
}
