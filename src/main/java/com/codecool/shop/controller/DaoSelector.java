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

    static SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();
    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
    static ProductDao productDataStore = ProductDaoJDBC.getInstance();
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
