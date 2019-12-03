package com.example.fluxit.model;

import java.io.Serializable;

public class Location implements Serializable {

    private Street street;
    private Coordinates coordinates;

    public Street getStreet() {
        return street;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
