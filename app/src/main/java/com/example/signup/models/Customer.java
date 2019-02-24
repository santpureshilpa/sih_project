package com.example.signup.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {

String id;
  String name;
  String emailId;
  int phoneNo;
  String rationNo;
  String city;
  float longitude;
  float latitude;
  String password;
  int aadharNo;
  String voterNo;
  String panNo;

    protected Customer(Parcel in) {
        id = in.readString();
        name = in.readString();
        emailId = in.readString();
        phoneNo = in.readInt();
        city = in.readString();
        longitude = in.readFloat();
        latitude = in.readFloat();
        password = in.readString();
        aadharNo = in.readInt();
        voterNo = in.readString();
        panNo = in.readString();
        rationNo=in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(int aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getVoterNo() {
        return voterNo;
    }

    public void setVoterNo(String voterNo) {
        this.voterNo = voterNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getRationNo() {
        return rationNo;
    }

    public void setRationNo(String rationNo) {
        this.rationNo = rationNo;
    }

    public Customer() {
    }

    public Customer(String id, String name, String emailId, int phoneNo, String city, float longitude, float latitude, String password, int aadharNo, String voterNo, String panNo) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.password = password;
        this.aadharNo = aadharNo;
        this.voterNo = voterNo;
        this.panNo = panNo;
        this.rationNo=rationNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(emailId);
        dest.writeInt(phoneNo);
        dest.writeString(city);
        dest.writeFloat(longitude);
        dest.writeFloat(latitude);
        dest.writeString(password);
        dest.writeInt(aadharNo);
        dest.writeString(voterNo);
        dest.writeString(panNo);
        dest.writeString(rationNo);
    }
}
