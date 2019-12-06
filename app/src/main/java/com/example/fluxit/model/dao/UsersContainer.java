package com.example.fluxit.model.dao;

import com.example.fluxit.model.pojo.User;

import java.util.List;

public class UsersContainer {

    private List<User> results;
    private Integer page;

    public UsersContainer(List<User> results, Integer page) {
        this.results = results;
        this.page = page;
    }

    public UsersContainer() {
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
