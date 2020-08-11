package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

public interface OrderDao {

    Order addOrder(String sessionId);
    public Order find(String sessionId);
    void remove(String sessionId);

}
