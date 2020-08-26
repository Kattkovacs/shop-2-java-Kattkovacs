package com.codecool.shop.dao.memory;
import com.codecool.shop.dao.implementation_memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryDaoMemoryTest {

    private static ProductCategoryDao productCategoryDao;
    private static ProductCategory productCategoryOne;
    private static ProductCategory productCategoryTwo;

    @BeforeEach
    void setup() {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productCategoryOne = new ProductCategory("Test P.Category One", "Test P.Category One Department", "Test P.Category One Description");
        productCategoryTwo = new ProductCategory("Test P.Category Two", "Test P.Category Two Department", "Test P.Category Two Description");
        productCategoryDao.add(productCategoryOne);
        productCategoryDao.add(productCategoryTwo);
    }

    @Test
    public void add_addNewProductCategory_doesNotThrowAnException() {
        assertDoesNotThrow(() -> productCategoryDao.add(productCategoryOne));
    }
}
