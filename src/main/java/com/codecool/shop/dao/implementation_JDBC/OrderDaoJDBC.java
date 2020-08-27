package com.codecool.shop.dao.implementation_JDBC;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Order> find(User user) {
        List<Order> orders = ReadOrders(user.getId());
        return orders;
    }

    private List<Order> ReadOrders(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM \"order\" WHERE user_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            return getOrders(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    private List<Order> getOrders(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            Order order = getOrder(resultSet);
            orders.add(order);

        }
        return orders;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(resultSet.getString("first_name"));
        userDetails.setLastName(resultSet.getString("last_name"));
        userDetails.setInputEmail(resultSet.getString("email"));
        userDetails.setInputPhone(resultSet.getString("phone"));
        userDetails.setInputAddress(resultSet.getString("address"));
        userDetails.setInputCity(resultSet.getString("city"));
        userDetails.setInputState(resultSet.getString("state"));
        userDetails.setInputZip(resultSet.getInt("zip"));
        userDetails.setInputAddress2(resultSet.getString("address2"));
        userDetails.setInputCity2(resultSet.getString("city2"));
        userDetails.setInputState2(resultSet.getString("state2"));
        userDetails.setInputZip2(resultSet.getInt("zip2"));
        Order order = new Order();
        order.setUserDetails(userDetails);
        return order;
    }

    @Override
    public void remove(int id) {

    }
}
