package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderDaoMem implements OrderDao {

    private Map<String, Order> orders = new HashMap<>();
    private static OrderDaoMem instance = null;

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public Order addOrder(String sessionId) {
        Order newOrder = new Order();
        orders.put(sessionId, newOrder);
        return newOrder;
    }

    @Override
    public Order find(String sessionId) {
        if (orders.containsKey(sessionId)) return orders.get(sessionId);
        return addOrder(sessionId);
    }

    @Override
    public void remove(String sessionId) {
        orders.remove(sessionId);
    }
}
