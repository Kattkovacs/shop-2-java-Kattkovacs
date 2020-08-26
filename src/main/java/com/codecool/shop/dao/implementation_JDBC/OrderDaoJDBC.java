package com.codecool.shop.dao.implementation_JDBC;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

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
        int id = writeOutOrder(order);
        for (LineItem lineItem: order.getLineItemList()) {
            writeOutLineItem(id, lineItem);
        }
        return order;
    }

    private void writeOutLineItem(int id, LineItem lineItem) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO order_details (order_id, product_id, quantity, total_product_price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.setInt(2, lineItem.getProduct().getId());
            statement.setInt(3, lineItem.getQuantity());
            statement.setFloat(4, lineItem.getTotalProductPriceInFloat());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    private int writeOutOrder(Order order) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO \"order\" (total_order_price, first_name, last_name, email, phone, " +
                    "address, city, state, zip, address2, city2, state2, zip2) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setFloat(1, order.getTotalOrderPriceInFloat());
            statement.setString(2, order.getUserDetails().getFirstName());
            statement.setString(3, order.getUserDetails().getLastName());
            statement.setString(4, order.getUserDetails().getInputEmail());
            statement.setString(5, order.getUserDetails().getInputPhone());
            statement.setString(6, order.getUserDetails().getInputAddress());
            statement.setString(7, order.getUserDetails().getInputCity());
            statement.setString(8, order.getUserDetails().getInputState());
            statement.setInt(9, order.getUserDetails().getInputZip());
            statement.setString(10, order.getUserDetails().getInputAddress2());
            statement.setString(11, order.getUserDetails().getInputCity2());
            statement.setString(12, order.getUserDetails().getInputState2());
            statement.setInt(13, order.getUserDetails().getInputZip2());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public Order find(User user) {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
