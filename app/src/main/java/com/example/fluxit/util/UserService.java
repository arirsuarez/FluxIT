package com.example.fluxit.util;

import com.example.fluxit.model.dao.UsersContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("seed=abc")
    Call<UsersContainer> userApiRequest(@Query("page") int page);


}
