package com.example.sparksupportmt.Interface;

import com.example.sparksupportmt.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageResponse {
    @GET("dashboard/")
    Call<List<User>> getUsers();
}
