package com.example.sparksupportmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {


    private String image;



    public User(String image) {


        this.image = image;

    }
    public String getImage() {
        return image;
    }


    }
