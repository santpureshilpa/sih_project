package com.example.signup.models;

public class OTPSentResponse {
    String Status;
    String Details;

    public OTPSentResponse(String status, String details) {
        Status = status;
        Details = details;
    }

    public OTPSentResponse() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
