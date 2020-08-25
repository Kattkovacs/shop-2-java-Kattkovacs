package com.codecool.shop.dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ShopDatabaseManager {

    private static ShopDatabaseManager instance = null;
    private final Properties properties = new Properties();;
    private DataSource dataSource;

    public ShopDatabaseManager() {

        try {
            setup();
            dataSource = connect();
        } catch (SQLException e) {
            System.out.println("SQL error" + e);
        } catch (IOException e) {
            System.out.println("IO error" + e);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static ShopDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ShopDatabaseManager();
        }
        return instance;
    }

    private void setup() throws SQLException, IOException {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String connectionPropertiesPath = rootPath + "connection.properties";

        properties.load(new FileInputStream(connectionPropertiesPath));

    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = properties.getProperty("database");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }


}
