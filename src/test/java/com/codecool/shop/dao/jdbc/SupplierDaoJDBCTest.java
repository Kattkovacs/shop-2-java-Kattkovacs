package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.implementation_JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierDaoJDBCTest {
    public static final String CONNECTION_TEST_PROPERTIES = "connection_test.properties";
    private static SupplierDao supplierDao;
    private static Supplier supplierOne;
    private static Supplier supplierTwo;


    @BeforeEach
    void setup() {
        resetDatabase();
        supplierDao = SupplierDaoJDBC.getInstance(CONNECTION_TEST_PROPERTIES);
        supplierOne = new Supplier("Test Supplier One", "Test Supplier One Description");
        supplierTwo = new Supplier("Test Supplier Two", "Test Supplier Two Description");
    }

    @Test
    public void add_addNewSupplier_doesNotThrowAnException() {
        assertDoesNotThrow(() -> supplierDao.add(supplierOne));
    }

    @Test
    public void add_addSupplierWithNullArgument_throwIllegalArgumentException() {
        Supplier supplierThree = new Supplier(null, "Description");
        Supplier supplierFour = new Supplier("Name", null);
        assertThrows(IllegalArgumentException.class, () -> supplierDao.add(supplierThree));
        assertThrows(IllegalArgumentException.class, () -> supplierDao.add(supplierFour));
    }

    @Test
    public void find_findFirstId_returnTestSupplierOne() {
        supplierDao.add(supplierOne);
        Supplier supplier = supplierDao.find(1);
        assertEquals(supplierOne.getName(), supplier.getName());
        assertEquals(supplierOne.getDescription(), supplier.getDescription());
    }


    @Test
    public void find_findSecondId_returnTestSupplierTwo() {
        supplierDao.add(supplierOne);
        supplierDao.add(supplierTwo);
        Supplier supplier = supplierDao.find(2);
        assertEquals(supplierTwo.getName(), supplier.getName());
        assertEquals(supplierTwo.getDescription(), supplier.getDescription());
    }


    @AfterAll
    static void resetDatabase() {
        ShopDatabaseManager shopDatabaseManager = ShopDatabaseManager.getInstance(CONNECTION_TEST_PROPERTIES);
        DataSource dataSource = shopDatabaseManager.getDataSource();

        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier; ALTER SEQUENCE supplier_id_seq RESTART;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

}
