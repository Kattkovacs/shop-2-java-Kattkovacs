package com.codecool.shop.dao.memory;
import com.codecool.shop.dao.implementation_memory.ProductDaoMem;
import com.codecool.shop.dao.interfaces.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductDaoMemoryTest {

    private static ProductDao productDao;
    private static Product productOne;
    private static Product productTwo;
    private static ProductCategory productCategoryOne;
    private static Supplier supplierOne;

    @BeforeEach
    void setup() {
        productDao = ProductDaoMem.getInstance();
        supplierOne = new Supplier("Test Supplier One", "Test Supplier One Description");
        productCategoryOne = new ProductCategory("Test P.Category One", "Test P.Category One Department", "Test P.Category One Description");
        productOne = new Product("Test Product One", 9.9f, "USD", "Test Product One Description", productCategoryOne, supplierOne);
        productTwo = new Product("Test Product Two", 9.8f, "USD", "Test Product Two Description",  productCategoryOne, supplierOne);
    }

    @Test
    public void add_addNewProduct_doesNotThrowAnException() {
        assertDoesNotThrow(() -> productDao.add(productOne));
    }

    @Test
    public void add_addNewProductWithZeroDefaultPrice_throwAnIllegalArgumentException() {
        Product productZero = new Product("Test Product One", 0.0f, "USD", "Test Product One Description", productCategoryOne, supplierOne);
        assertThrows(IllegalArgumentException.class, () -> productDao.add(productZero));
    }
}
