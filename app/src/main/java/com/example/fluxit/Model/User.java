package com.example.fluxit.Model;

import java.util.jar.Attributes;

public class User {

    private String email;
    private Login login;
    private Picture picture;
    private Name name;
    private Dob dob;
    private Location location;
    private Coordinates coordinates;

    public String getEmail() {
        return email;
    }

    public Login getLogin() {
        return login;
    }

    public Picture getPicture() {
        return picture;
    }

    public Name getName() {
        return name;
    }

    public Dob getDob() {
        return dob;
    }

    public Location getLocation() {
        return location;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}

