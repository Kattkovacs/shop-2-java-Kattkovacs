package com.codecool.shop.dao.jdbc;
import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.implementation_JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation_JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation_JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.dao.interfaces.ProductDao;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDaoJDBCTest {

    public static final String CONNECTION_TEST_PROPERTIES = "connection_test.properties";
    private static ProductCategoryDao productCategoryDao;
    private static SupplierDao supplierDao;
    private static ProductDao productDao;
    private static ProductCategory productCategoryOne;
    private static Supplier supplierOne;
    private static Product productOne;
    private static Product productTwo;

    @BeforeAll
    static void init() {
        productCategoryDao = ProductCategoryDaoJDBC.getInstance(CONNECTION_TEST_PROPERTIES);
        supplierDao = SupplierDaoJDBC.getInstance(CONNECTION_TEST_PROPERTIES);
        productDao = ProductDaoJDBC.getInstance(CONNECTION_TEST_PROPERTIES);
        productCategoryOne  = new ProductCategory("Test P.Category One", "Test P.Category One Department", "Test P.Category One Description");
        supplierOne = new Supplier("Test Supplier One", "Test Supplier One Description");
        productOne  = new Product("Test Product One", 9.9f, "USD", "Test Product One Description", productCategoryOne, supplierOne);
        productTwo = new Product("Test Product Two", 9.8f, "USD", "Test Product Two Description", productCategoryOne, supplierOne);
        productCategoryOne.setId(1);
        supplierOne.setId(1);
    }


    @BeforeEach
    void setup() {
        resetDatabase();
    }


    @Test
    public void add_addNewProductWithZeroDefaultPrice_throwAnIllegalArgumentException() {
        Product productZero = new Product("Test Product One", 0.0f, "USD", "Test Product One Description", productCategoryOne, supplierOne);
        assertThrows(IllegalArgumentException.class, () -> productDao.add(productZero));
    }

    @Test
    public void find_productIdOne_returnsProductOne() {
        productCategoryDao.add(productCategoryOne);
        supplierDao.add(supplierOne);
        productDao.add(productOne);
        Product product = productDao.find(1);
        assertEquals(productOne.getName(), product.getName());
    }


    @AfterAll
    static void resetDatabase() {
        ShopDatabaseManager shopDatabaseManager = ShopDatabaseManager.getInstance(CONNECTION_TEST_PROPERTIES);
        DataSource dataSource = shopDatabaseManager.getDataSource();

        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product; ALTER SEQUENCE product_id_seq RESTART;" +
                    "DELETE FROM supplier; ALTER SEQUENCE supplier_id_seq RESTART;" +
                    "DELETE FROM product_category; ALTER SEQUENCE product_category_id_seq RESTART;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }
}
