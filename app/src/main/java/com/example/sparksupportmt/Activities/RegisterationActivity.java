package com.example.sparksupportmt.Activities;

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

import com.example.sparksupportmt.ViewModel.MainViewModel;
import com.example.sparksupportmt.R;
import com.example.sparksupportmt.Model.RegisterBody;
import com.example.sparksupportmt.Response.RegisterResponse;

public class RegisterationActivity extends AppCompatActivity {
    EditText etEmail, etPassword, confirmPassword;
    Button button_register,button_register_signup;
    ProgressBar progressBar;
    MainViewModel mViewModel;
    CheckBox login_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registeration );

        initViewModel();
        etEmail = findViewById( R.id.register_email_et );
        etPassword = findViewById( R.id.register_password_et );
        confirmPassword = findViewById( R.id.register_confirmpassword_et );
        button_register = findViewById( R.id.button_register );
        button_register_signup = findViewById( R.id.button_register_signup );
        login_checkbox=findViewById(R.id.login_checkbox);

        mViewModel = new ViewModelProvider( this ).get( MainViewModel.class );

        login_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    etPassword.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                    confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    etPassword.setTransformationMethod( PasswordTransformationMethod.getInstance());
                    confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        button_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
 }
        } );
        button_register_signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity( i );
            }
        } );


    }
    private boolean validateName() {
        String val = ((EditText) findViewById( R.id.name_et )).getText().toString();
        String noWhitespace = "(?=\\+$&)";
        if (val.isEmpty()) {
            ((EditText) findViewById( R.id.name_et )).setError( "Field cannot be empty" );
            return false;
        } else if (val.length() >= 15) {
            ((EditText) findViewById( R.id.name_et )).setError( "Username too long" );
            return false;
        } else {
            ((EditText) findViewById( R.id.name_et )).setError( null );
            return true;
        }
    }
    private boolean validateFirstName(){
        String val = ((EditText) findViewById( R.id.fname_et )).getText().toString();
        if (val.isEmpty()){
            ((EditText) findViewById( R.id.fname_et )).setError( "Field cannot be empty" );
            return false;
        }
        else{
            ((EditText) findViewById( R.id.fname_et )).setError( null );
            return true;
        }
    }
    private boolean validateLastName(){
        String val = ((EditText) findViewById( R.id.lname_et )).getText().toString();
        if (val.isEmpty()){
            ((EditText) findViewById( R.id.lname_et )).setError( "Field cannot be empty" );
            return false;
        }
        else{
            ((EditText) findViewById( R.id.lname_et )).setError( null );
            return true;
        }
    }
    private boolean validateEmail() {
        String val = ((EditText) findViewById( R.id.register_email_et )).getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            ((EditText) findViewById( R.id.register_email_et )).setError( "Field cannot be empty" );
            return false;
        } else if (!val.matches( emailPattern )) {
            ((EditText) findViewById( R.id.register_email_et )).setError( "invalid email address" );
            return false;
        } else {
            ((EditText) findViewById( R.id.register_email_et )).setError( null );
            return true;
        }
    }
    private boolean validatepass() {
        String val = ((EditText) findViewById( R.id.register_password_et )).getText().toString();

        if (val.isEmpty()) {
            ((EditText) findViewById( R.id.register_password_et )).setError( "Field cannot be empty" );
            return false;
        } else {
            ((EditText) findViewById( R.id.register_password_et )).setError( null );
            return true;
        }
    }
    private boolean validateconfirmpass() {
        String val = ((EditText) findViewById( R.id.register_confirmpassword_et )).getText().toString();

        if (val.isEmpty()) {
            ((EditText) findViewById( R.id.register_confirmpassword_et )).setError( "Field cannot be empty" );
            return false;
        } else {
            ((EditText) findViewById( R.id.register_password_et )).setError( null );
            return true;
        }
    }


    private void createNewUser() {
        if(!validateName() | !validateEmail() | ! validateFirstName()| ! validateLastName()| ! validatepass()| ! validateconfirmpass())
        {
            return;
        }
         Intent i = new Intent(getApplicationContext(),LoginActivity.class);
         startActivity( i );

        String email = ((EditText) findViewById( R.id.register_email_et )).getText().toString();
        String pw = ((EditText) findViewById( R.id.register_password_et )).getText().toString();
        String cp = ((EditText) findViewById( R.id.register_confirmpassword_et )).getText().toString();
        String username = ((EditText) findViewById( R.id.name_et )).getText().toString();
        String fname = ((EditText) findViewById( R.id.fname_et )).getText().toString();
        String lname = ((EditText) findViewById( R.id.lname_et )).getText().toString();
        RegisterBody user = new RegisterBody( email, pw, cp,username,fname,lname);
        mViewModel.createNewUser( user );
    }

    private void initViewModel() {

        mViewModel = new ViewModelProvider( this ).get( MainViewModel.class );
        mViewModel.getCreateUserObserver().observe( this, new Observer<RegisterResponse>() {

            @Override
            public void onChanged(RegisterResponse registerResponse) {
                if(registerResponse == null) {
                    Toast.makeText(RegisterationActivity.this, "failed to create new user", Toast.LENGTH_LONG).show();
                } else {
                   Toast.makeText(RegisterationActivity.this, "Successfully created new user", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}