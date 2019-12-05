package com.example.fluxit.model.pojo;

import java.io.Serializable;

public class Picture implements Serializable {

    private String large;
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
