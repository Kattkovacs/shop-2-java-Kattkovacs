package com.codecool.shop.model;

public class User extends BaseModel{

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String name, String description) {
        super(name, description);
    }
}
