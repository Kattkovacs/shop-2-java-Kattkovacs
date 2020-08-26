package com.codecool.shop.dao.interfaces;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

public interface OrderDao {

    Order addOrder(Order order);
    public Order find(User user);
    void remove(int id);

}
