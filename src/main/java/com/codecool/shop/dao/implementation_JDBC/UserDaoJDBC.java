package com.codecool.shop.dao.implementation_JDBC;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.interfaces.UserDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJDBC implements UserDao {

    private static UserDaoJDBC instance = null;
    private final DataSource dataSource;

    public static UserDaoJDBC getInstance(String connectionProperties) {
        if (instance == null) {
            instance = new UserDaoJDBC(connectionProperties);
        }
        return instance;
    }

    private UserDaoJDBC(String connectionProperties) {
        this.dataSource = ShopDatabaseManager.getInstance(connectionProperties).getDataSource();
    }

    @Override
    public void add(User user, String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO "user" (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, password);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public User find(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT first_name, last_name, email FROM \"user\" WHERE email = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new User(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public String login(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT password FROM \"user\" WHERE email = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public void remove(String email) {

    }
}
