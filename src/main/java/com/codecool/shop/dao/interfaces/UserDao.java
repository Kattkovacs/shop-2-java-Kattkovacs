package com.codecool.shop.dao.interfaces;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {

    void add(User user, String password);
    User find(String email);
    String login(String email);
    void remove(String email);
}
