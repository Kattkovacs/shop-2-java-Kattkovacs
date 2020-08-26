package com.codecool.shop.dao.interfaces;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    User find(String email);
    void remove(int id);

    List<User> getAll();
}
