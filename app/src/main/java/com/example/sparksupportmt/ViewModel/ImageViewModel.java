package com.example.sparksupportmt.ViewModel;

import android.util.Log;

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

public class ImageViewModel extends ViewModel {
    private MutableLiveData<List<User>> imagesLiveData;
    private ImageResponse apiService;

    public ImageViewModel() {
        imagesLiveData = new MutableLiveData<>();
        apiService = RetrofitClientInstance.getInstance().create(ImageResponse.class);
    }

    public LiveData<List<User>> getImagesLiveData() {
        return imagesLiveData;
    }

    public void fetchImages() {
        Call<List<User>> call = apiService.getUsers("Authorization");
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    imagesLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e("API", "Error fetching images", t);
            }
        });
    }
}