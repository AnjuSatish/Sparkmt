package com.example.sparksupportmt.Activities;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

import com.example.sparksupportmt.Model.LoginBody;
import com.example.sparksupportmt.Model.RegisterBody;
import com.example.sparksupportmt.Response.LoginResponse;
import com.example.sparksupportmt.Response.RegisterResponse;
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
                        if(!validateName() | ! validatepass())
                        {
                            return;
                        }
                        mViewModel1.login(etEmail.getText().toString(), etPassword.getText().toString());
                       if(etPassword  !=null && etEmail !=null){
                           Intent i = new Intent(getApplicationContext(),MainActivity.class);
                           startActivity(i);

                       }
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
    private boolean validateName() {
        String val = ((EditText) findViewById( R.id.login_email_et )).getText().toString();
        String noWhitespace = "(?=\\+$&)";
        if (val.isEmpty()) {
            ((EditText) findViewById( R.id.login_email_et )).setError( "Field cannot be empty" );
            return false;
        } else if (val.length() >= 15) {
            ((EditText) findViewById( R.id.login_email_et )).setError( "Username too long" );
            return false;
        } else {
            ((EditText) findViewById( R.id.login_email_et )).setError( null );
            return true;
        }
    }
    private boolean validatepass() {
        String val = ((EditText) findViewById( R.id.login_password_et )).getText().toString();

        if (val.isEmpty()) {
            ((EditText) findViewById( R.id.login_password_et )).setError( "Field cannot be empty" );
            return false;
        } else {
            ((EditText) findViewById( R.id.login_password_et )).setError( null );
            return true;
        }
    }


//    private boolean validateName() {
//        String val = ((EditText) findViewById( R.id.login_email_et )).getText().toString();
//        String noWhitespace = "(?=\\+$&)";
//        if (val.isEmpty()) {
//            ((EditText) findViewById( R.id.login_email_et )).setError( "Field cannot be empty" );
//            return false;
//        } else if (val.length() >= 15) {
//            ((EditText) findViewById( R.id.login_email_et )).setError( "Username too long" );
//            return false;
//        } else {
//            ((EditText) findViewById( R.id.login_email_et )).setError( null );
//            return true;
//        }
//    }
//    private boolean validatepass() {
//        String val = ((EditText) findViewById( R.id.login_password_et )).getText().toString();
//
//        if (val.isEmpty()) {
//            ((EditText) findViewById( R.id.login_password_et )).setError( "Field cannot be empty" );
//            return false;
//        } else {
//            ((EditText) findViewById( R.id.login_password_et )).setError( null );
//            return true;
//        }
//    }
    }
