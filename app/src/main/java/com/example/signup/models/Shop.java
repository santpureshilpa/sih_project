package com.example.signup.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Shop implements Parcelable {
    String id;
    String name;
    String licenseNo;
    String address;
    float latitude;
    float longitude;
    int credit;
    String distributorId;
    List<Product> products;

    public Shop() {
    }

    public Shop(String id, String name, String licenseNo, String address, float latitude, float longitude, int credit, String distributorId, List<Product> products) {
        this.id = id;
        this.name = name;
        this.licenseNo = licenseNo;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.credit = credit;
        this.distributorId = distributorId;
        this.products = products;
    }

    protected Shop(Parcel in) {
        id = in.readString();
        name = in.readString();
        licenseNo = in.readString();
        address = in.readString();
        latitude = in.readFloat();
        longitude = in.readFloat();
        credit = in.readInt();
        distributorId = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


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

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(licenseNo);
        dest.writeString(address);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeInt(credit);
        dest.writeString(distributorId);
        dest.writeTypedList(products);
    }
}
