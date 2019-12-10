package com.example.fluxit.util;

import com.example.fluxit.model.dao.UsersContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("/api")
    Call<UsersContainer> userApiRequest(@Query("page") Integer page,
                                        @Query("results") Integer results,
                                        @Query("seed") String seed);


}
