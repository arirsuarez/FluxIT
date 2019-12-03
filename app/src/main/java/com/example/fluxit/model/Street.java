package com.example.fluxit.model;

import java.io.Serializable;

public class Street implements Serializable {

    private String name;
    private Integer number;

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }
}
