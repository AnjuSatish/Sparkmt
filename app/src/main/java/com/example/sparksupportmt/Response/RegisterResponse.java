package com.example.sparksupportmt.Response;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("success")
    String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }}
