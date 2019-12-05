package com.example.fluxit.model.dao;

import com.example.fluxit.model.pojo.User;

import java.util.List;

public class UsersContainer {

    private List<User> results;

    public UsersContainer(List<User> results) {
        this.results = results;
    }

    public UsersContainer() {
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
