package com.example.fluxit.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

    @SerializedName("results")
    @Expose
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }
}
