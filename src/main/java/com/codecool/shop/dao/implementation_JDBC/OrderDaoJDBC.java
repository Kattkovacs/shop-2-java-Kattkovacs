package com.codecool.shop.dao.implementation_JDBC;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class OrderDaoJDBC implements OrderDao {

    private static OrderDao instance = null;
    private final DataSource dataSource;

    private OrderDaoJDBC(String connectionProperties) {
        this.dataSource = ShopDatabaseManager.getInstance(connectionProperties).getDataSource();
    }

    public static OrderDao getInstance(String connectionProperties) {
        if (instance == null) {
            instance = new OrderDaoJDBC(connectionProperties);
        }
        return instance;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public Order find(User user) {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
