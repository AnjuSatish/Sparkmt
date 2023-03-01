package com.example.sparksupportmt.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sparksupportmt.Interface.ImageResponse;
import com.example.sparksupportmt.Model.User;
import com.example.sparksupportmt.Response.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageViewModel extends ViewModel {
    private MutableLiveData<List<User>> imageList;
    public LiveData<List<User>> getImage() {
        //if the list is null
        if (imageList == null) {
            imageList = new MutableLiveData<List<User>>();
            //we will load it asynchronously from server in this method
            loadImage();
        }

        //finally we will return the list
        return imageList;
}

    private void loadImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( RetrofitClientInstance.BASE_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageResponse api = retrofit.create(ImageResponse.class);
        Call<List<User>> call = api.getUsers("Authorization");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                imageList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
