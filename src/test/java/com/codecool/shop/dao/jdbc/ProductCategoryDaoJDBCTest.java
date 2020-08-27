package com.codecool.shop.dao.jdbc;
import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.implementation_JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryDaoJDBCTest {

    public static final String CONNECTION_TEST_PROPERTIES = "connection_test.properties";
    private static ProductCategoryDao productCategoryDao;
    private static ProductCategory productCategoryOne;
    private static ProductCategory productCategoryTwo;

    @BeforeEach
    void setup() {
        resetDatabase();
        productCategoryDao = ProductCategoryDaoJDBC.getInstance(CONNECTION_TEST_PROPERTIES);
        productCategoryOne = new ProductCategory("Test P.Category One", "Test P.Category One Department", "Test P.Category One Description");
        productCategoryTwo = new ProductCategory("Test P.Category Two", "Test P.Category Two Department", "Test P.Category Two Description");
    }

    @org.junit.jupiter.api.Test
    public void add_addNewProductCategory_doesNotThrowAnException() {
        assertDoesNotThrow(() -> productCategoryDao.add(productCategoryOne));
    }

    @Test
    public void add_addProductCategoryWithNullArgument_throwIllegalArgumentException() {
        ProductCategory productCategoryThree = new ProductCategory(null, "Department", "Description");
        ProductCategory productCategoryFour = new ProductCategory("Name", null, "Description");
        ProductCategory productCategoryFive = new ProductCategory("Name", "Department", null);
        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.add(productCategoryThree));
        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.add(productCategoryFour));
        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.add(productCategoryFive));
    }

    @Test
    public void find_findFirstId_returnTestProductCategoryOne() {
        productCategoryDao.add(productCategoryOne);
        productCategoryDao.add(productCategoryTwo);
        ProductCategory productCategory = productCategoryDao.find(1);
        assertEquals(productCategory.getName(), productCategoryOne.getName());
        assertEquals(productCategory.getDepartment(), productCategoryOne.getDepartment());
        assertEquals(productCategory.getDescription(), productCategoryOne.getDescription());
    }

    @Test
    public void getAll_getAllProductCategory_hasLengthTwo() {
        productCategoryDao.add(productCategoryTwo);
        productCategoryDao.add(productCategoryOne);
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        assertEquals(2, productCategories.size());
    }


    @AfterAll
    static void resetDatabase() {
        ShopDatabaseManager shopDatabaseManager = ShopDatabaseManager.getInstance(CONNECTION_TEST_PROPERTIES);
        DataSource dataSource = shopDatabaseManager.getDataSource();

        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product_category; ALTER SEQUENCE product_category_id_seq RESTART;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }
}
