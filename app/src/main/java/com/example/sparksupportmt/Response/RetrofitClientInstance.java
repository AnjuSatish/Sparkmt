package com.example.sparksupportmt.Response;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    public static String BASE_URL = "http://54.36.143.60:8000/";
    private static Retrofit retrofit;

    // https://reqres.in/api/login

    public static Retrofit getInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel( HttpLoggingInterceptor.Level.BODY );
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor( new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " )
                        .build();
                return chain.proceed(newRequest);

            }
        } ).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .client( client )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();

        return retrofit;

    }
}
