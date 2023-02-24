package com.example.sparksupportmt.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    private String email;

    private String password;

    private String password2;

    private String first_name;


    private String last_name;

    private String username;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RegisterBody(String email, String password, String password2, String first_name, String last_name, String username) {
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;

    }


}
