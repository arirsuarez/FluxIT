package com.example.fluxit.controller;

import com.example.fluxit.UserInfoActivity;
import com.example.fluxit.model.dao.UsersContainer;
import com.example.fluxit.model.dao.UsersDao;
import com.example.fluxit.util.ResultListener;

public class UserController {

    public static final Integer RESULTS = 20;
    public static final String SEED = "abc";

    private UsersDao dao;
    private Integer page;
    private Boolean loading;


    public UserController() {
        dao = new UsersDao();
        page = 1;
        loading = false;
    }

    public void userApiRequest(final ResultListener<UsersContainer> viewListener) {

        if (!loading) {
            loading = true;
            dao.userRequestDao(new ResultListener<UsersContainer>() {
                @Override
                public void onFinish(UsersContainer results) {
                    viewListener.onFinish(results);
                    page++;
                    loading = false;
                }
            },page,RESULTS,SEED);
        }
    }

}
