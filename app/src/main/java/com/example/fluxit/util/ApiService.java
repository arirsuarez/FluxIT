package com.example.fluxit.util;

import com.example.fluxit.model.pojo.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/?page=1&results=20&seed=abc")
    Call<Results> getApiResults();


}
