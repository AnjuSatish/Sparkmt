package com.example.sparksupportmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    private String image_link;

    public User(String image_link) {
        this.image_link = image_link;
    }
    public String getImage_link() {
        return image_link;
    }
    }
