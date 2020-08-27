package com.codecool.shop.dao.interfaces;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);
    public List<Order> find(User user);
    void remove(int id);

}
