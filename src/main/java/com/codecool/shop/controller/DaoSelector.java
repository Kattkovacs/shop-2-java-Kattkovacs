package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation_JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation_JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation_JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation_memory.OrderDaoMem;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.dao.interfaces.ProductDao;
import com.codecool.shop.dao.interfaces.SupplierDao;

public class DaoSelector {

    private final static String CONNECTION_PROPERTIES = "connection.properties";

    static SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static ProductDao productDataStore = ProductDaoJDBC.getInstance(CONNECTION_PROPERTIES);
    static OrderDao orderDataStore = OrderDaoMem.getInstance();

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
}
