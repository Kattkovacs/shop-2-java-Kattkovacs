package com.codecool.shop.dao;

import com.codecool.shop.dao.interfaces.OrderCacheDao;
import com.codecool.shop.model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderDaoCache implements OrderCacheDao {

    private Map<String, Order> orders = new HashMap<>();
    private static OrderDaoCache instance = null;

    public static OrderDaoCache getInstance() {
        if (instance == null) {
            instance = new OrderDaoCache();
        }
        return instance;
    }


    public Order addOrder(String sessionId) {
        Order newOrder = new Order();
        orders.put(sessionId, newOrder);
        return newOrder;
    }


    public Order find(String sessionId) {
        if (orders.containsKey(sessionId)) return orders.get(sessionId);
        return addOrder(sessionId);
    }


    public void remove(String sessionId) {
        orders.remove(sessionId);
    }
}
