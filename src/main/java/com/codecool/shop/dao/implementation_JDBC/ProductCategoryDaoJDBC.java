package com.codecool.shop.dao.implementation_JDBC;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private static ProductCategoryDaoJDBC instance = null;
    private final DataSource dataSource = ShopDatabaseManager.getInstance().getDataSource();

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_category (name, description, department) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, productCategory.getName());
            statement.setString(2, productCategory.getDescription());
            statement.executeUpdate();
            //Read answer from DataBase
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            productCategory.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product_category WHERE id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            ProductCategory productCategory = new ProductCategory(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("department")
            );
            productCategory.setId(resultSet.getInt("id"));
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product_category;";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<ProductCategory> productCategories = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department")
                );
                productCategory.setId(resultSet.getInt("id"));
                productCategories.add(productCategory);
            }
            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }
}
