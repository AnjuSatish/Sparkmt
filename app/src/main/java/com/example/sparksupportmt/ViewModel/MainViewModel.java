package com.example.sparksupportmt.ViewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sparksupportmt.Interface.IRegisterResponse;
import com.example.sparksupportmt.Adapter.MainRepository;
import com.example.sparksupportmt.Model.LoginBody;
import com.example.sparksupportmt.Model.RegisterBody;
import com.example.sparksupportmt.Response.LoginResponse;
import com.example.sparksupportmt.Response.RegisterResponse;
import com.example.sparksupportmt.Response.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<RegisterResponse> createNewUserLiveData;


    MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    MutableLiveData<String> mLoginResultMutableData = new MutableLiveData<>();

    MainRepository mMainRepository;
    public MutableLiveData<RegisterResponse > getCreateUserObserver(){
        return createNewUserLiveData;
    }

    public void createNewUser(RegisterBody user) {
        IRegisterResponse retroServiceInterface = RetrofitClientInstance.getInstance().create( IRegisterResponse.class );
        Call<RegisterResponse> call = retroServiceInterface.register( user );
        call.enqueue( new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> user, Response<RegisterResponse> response) {
                if(response.isSuccessful()) {
                    createNewUserLiveData.postValue(response.body());
                } else {
                    createNewUserLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                createNewUserLiveData.postValue(null);
            }
        } );
    }
    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mLoginResultMutableData.postValue("Not logged in");
        mMainRepository = new MainRepository();
        createNewUserLiveData = new MutableLiveData<>();
    }

    public void login(String username, String password) {
        mProgressMutableData.postValue(View.VISIBLE);
        mMainRepository.loginRemote(new LoginBody(username, password), new MainRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Success");
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login failure: " + t.getLocalizedMessage());
            }
        });
    }



    public LiveData<String> getLoginResult() {
        return mLoginResultMutableData;
    }

    public LiveData<Integer> getProgress() {
        return mProgressMutableData;
    }

}

