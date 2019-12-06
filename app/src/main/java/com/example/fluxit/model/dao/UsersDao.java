package com.example.fluxit.model.dao;

import com.example.fluxit.api.ApiClient;
import com.example.fluxit.util.ResultListener;
import com.example.fluxit.util.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersDao {

    private Retrofit retrofit;
    public static final String BASE_URL = "https://randomuser.me/api/";
    private UserService userService;

    public UsersDao() {

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         userService = retrofit.create(UserService.class);
    }

    public void userRequestDao (final ResultListener<UsersContainer> controllerListener){

        Call<UsersContainer> usersContainerCall = userService.userApiRequest();

        usersContainerCall.enqueue(new Callback<UsersContainer>() {
            @Override
            public void onResponse(Call<UsersContainer> call, Response<UsersContainer> response) {
                UsersContainer body = response.body();

                controllerListener.onFinish(body);

            }

            @Override
            public void onFailure(Call<UsersContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}


