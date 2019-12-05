package com.example.fluxit.controller;

import com.example.fluxit.model.dao.UsersContainer;
import com.example.fluxit.model.dao.UsersDao;
import com.example.fluxit.util.ResultListener;

public class UserController {

    private UsersDao dao;

    public UserController() {
        dao = new UsersDao();
    }

    public void UsersApi(final ResultListener<UsersContainer> viewListener){
        dao.apiRequest(new ResultListener<UsersContainer>() {
            @Override
            public void onFinish(UsersContainer results) {
                viewListener.onFinish(results);
            }
        });
    }
}
