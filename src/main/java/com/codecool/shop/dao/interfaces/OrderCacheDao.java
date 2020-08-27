package com.codecool.shop.dao.interfaces;

import com.codecool.shop.model.Order;

public interface OrderCacheDao {
    Order addOrder(String sessionId);
    Order find(String sessionId);
    void remove(String sessionId);
}
