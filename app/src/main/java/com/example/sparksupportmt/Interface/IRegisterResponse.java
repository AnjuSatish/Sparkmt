package com.example.sparksupportmt.Interface;

import com.example.sparksupportmt.Model.RegisterBody;
import com.example.sparksupportmt.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRegisterResponse {
    @POST("register/")
    Call<RegisterResponse> register(@Body RegisterBody registerBody);
}
