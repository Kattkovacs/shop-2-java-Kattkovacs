package com.codecool.shop.dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ShopDatabaseManager {

    private static ShopDatabaseManager instance = null;
    private final Properties properties = new Properties();;
    private DataSource dataSource;
    private final String connectionProperties;

    public ShopDatabaseManager(String connectionProperties) {
        this.connectionProperties = connectionProperties;

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

    public static ShopDatabaseManager getInstance(String connectionProperties) {
        if (instance == null) {
            instance = new ShopDatabaseManager(connectionProperties);
        }
        return instance;
    }

    private void setup() throws SQLException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(connectionProperties).getFile());
        properties.load(new FileInputStream(file.getAbsolutePath()));
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
