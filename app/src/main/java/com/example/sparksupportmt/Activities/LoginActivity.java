package com.example.sparksupportmt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sparksupportmt.ViewModel.MainViewModel;
import com.example.sparksupportmt.R;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button bLogin,button_login_signup;
    ProgressBar progressBar;
    MainViewModel mViewModel1;
    CheckBox login_checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login2);
        progressBar = findViewById(R.id.progressbar_login);
        etEmail = findViewById(R.id.login_email_et);
        etPassword = findViewById(R.id.login_password_et);
        bLogin = findViewById(R.id.button_login);
        button_login_signup = findViewById(R.id.button_login_signup);
        login_checkbox=findViewById(R.id.login_checkbox);

        mViewModel1 = new ViewModelProvider(this).get(MainViewModel.class);

        login_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    etPassword.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                }
                else {
                    etPassword.setTransformationMethod( PasswordTransformationMethod.getInstance());
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username=etEmail.getText().toString();
                        String password=etPassword.getText().toString();
                        if(validateLogin(username, password)) {
                            //do login
                            mViewModel1.login(etEmail.getText().toString(), etPassword.getText().toString());
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                            Toast.makeText( LoginActivity.this, "Login Success", Toast.LENGTH_SHORT ).show();


                        }
                        // mViewModel1.login(etEmail.getText().toString(), etPassword.getText().toString());

                        else{
                            Toast.makeText( LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT ).show();
                        }


                    }
                });

            }
        });

        button_login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegisterationActivity.class);
                startActivity(intent);

            }
        });


    }
    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}