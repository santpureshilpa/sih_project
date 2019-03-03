package com.example.signup.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Distributor implements Parcelable {

    String id;
    String name;
    int aadharNo;
    long mobileNo;
    String emailId;
    String password;

    String shopId;
    String shopName;
    String shopLicenseNo;
    String shopAddress;
    float shopLatitude;
    float shopLongitude;
    int credit;
    String distributorId;
    List<Product> products;

    public Distributor() {
    }

    protected Distributor(Parcel in) {
        id = in.readString();
        name = in.readString();
        aadharNo = in.readInt();
        mobileNo = in.readLong();
        emailId = in.readString();
        shopId = in.readString();
        shopName = in.readString();
        shopLicenseNo = in.readString();
        shopAddress = in.readString();
        shopLatitude = in.readFloat();
        shopLongitude = in.readFloat();
        credit = in.readInt();
        distributorId = in.readString();
        password = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<Distributor> CREATOR = new Creator<Distributor>() {
        @Override
        public Distributor createFromParcel(Parcel in) {
            return new Distributor(in);
        }

        @Override
        public Distributor[] newArray(int size) {
            return new Distributor[size];
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

    public int getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(int aadharNo) {
        this.aadharNo = aadharNo;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLicenseNo() {
        return shopLicenseNo;
    }

    public void setShopLicenseNo(String shopLicenseNo) {
        this.shopLicenseNo = shopLicenseNo;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public float getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(float shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public float getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(float shopLongitude) {
        this.shopLongitude = shopLongitude;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(aadharNo);
        dest.writeLong(mobileNo);
        dest.writeString(emailId);
        dest.writeString(shopId);
        dest.writeString(shopName);
        dest.writeString(shopLicenseNo);
        dest.writeString(shopAddress);
        dest.writeFloat(shopLatitude);
        dest.writeFloat(shopLongitude);
        dest.writeInt(credit);
        dest.writeString(distributorId);
        dest.writeTypedList(products);
        dest.writeString(password);
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", aadharNo=" + aadharNo +
                ", mobileNo=" + mobileNo +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopLicenseNo='" + shopLicenseNo + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopLatitude=" + shopLatitude +
                ", shopLongitude=" + shopLongitude +
                ", credit=" + credit +
                ", distributorId='" + distributorId + '\'' +
                ", products=" + products +
                '}';
    }
}
