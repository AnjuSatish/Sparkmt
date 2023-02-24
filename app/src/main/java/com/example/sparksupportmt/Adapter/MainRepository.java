package com.example.sparksupportmt.Adapter;

import com.example.sparksupportmt.Interface.ILoginService;
import com.example.sparksupportmt.Model.LoginBody;
import com.example.sparksupportmt.Response.LoginResponse;
import com.example.sparksupportmt.Response.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {


    public MainRepository() {
    }

    public void loginRemote(LoginBody loginBody, ILoginResponse loginResponse){
        ILoginService loginService = RetrofitClientInstance.getInstance().create(ILoginService.class);
        Call<LoginResponse> initiateLogin = loginService.login(loginBody);


        initiateLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    loginResponse.onResponse(response.body());
                } else {
                    loginResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponse.onFailure(t);
            }
        });

    }




    public interface ILoginResponse{
        void onResponse(LoginResponse loginResponse);
        void onFailure(Throwable t);
    }

}
