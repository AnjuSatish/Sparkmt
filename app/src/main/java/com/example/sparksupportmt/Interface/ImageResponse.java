package com.example.sparksupportmt.Interface;

import com.example.sparksupportmt.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ImageResponse {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("dashboard/")
    Call<List<User>> getUsers(@Header("Authorization") String authHeader);
}
