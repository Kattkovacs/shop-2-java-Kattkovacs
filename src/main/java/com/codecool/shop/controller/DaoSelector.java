package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation_JDBC.*;
import com.codecool.shop.dao.OrderDaoCache;
import com.codecool.shop.dao.interfaces.*;

public class DaoSelector {

    private final static String CONNECTION_PROPERTIES = "connection.properties";

    static SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static ProductDao productDataStore = ProductDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static OrderDao orderDataStore = OrderDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static UserDao userDataStore = UserDaoJDBC.getInstance(CONNECTION_PROPERTIES);

    static OrderCacheDao orderCacheStore = OrderDaoCache.getInstance();

    public static SupplierDao getSupplierDataStore() {
        return supplierDataStore;
    }

    public static ProductCategoryDao getProductCategoryDataStore() {
        return productCategoryDataStore;
    }

    public static ProductDao getProductDataStore() {
        return productDataStore;
    }

    public static OrderDao getOrderDataStore() {
        return orderDataStore;
    }

    public static OrderCacheDao getOrderCacheStore() {
        return orderCacheStore;
    }

    public static UserDao getUserDataStore() { return userDataStore; }
}
