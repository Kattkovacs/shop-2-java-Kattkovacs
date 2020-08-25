package com.codecool.shop.dao.implementation_JDBC;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.dao.interfaces.ProductDao;

import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Filterable;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private static ProductDaoJDBC instance = null;
    private final DataSource dataSource = ShopDatabaseManager.getInstance().getDataSource();
    private final ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
    private final SupplierDao supplierDao = new SupplierDaoJDBC();

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (name, description, default_price, default_currency) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, String.valueOf(product.getDefaultCurrency()));
            statement.executeUpdate();
            //Read answer from DataBase
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Product product = getProduct(resultSet);
            product.setId(resultSet.getInt("id"));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategoryDao.find(resultSet.getInt("product_category_id")),
                        supplierDao.find(resultSet.getInt("supplier_id"))
                );
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
            SupplierDao supplierDao = new SupplierDaoJDBC();
            String sql = "SELECT * FROM product;";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
            SupplierDao supplierDao = new SupplierDaoJDBC();
            String sql = "SELECT * FROM product WHERE supplier_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, supplier.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
            SupplierDao supplierDao = new SupplierDaoJDBC();
            String sql = "SELECT * FROM product WHERE product_category_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

}


