package com.example.fluxit.Interfaces;

import com.example.fluxit.model.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/?results=20&seed=abc")
    Call<Results> getApiResults();
}
