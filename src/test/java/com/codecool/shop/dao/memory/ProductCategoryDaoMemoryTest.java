package com.codecool.shop.dao.memory;
import com.codecool.shop.dao.implementation_memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void add_addSupplierWithNullArgument_throwIllegalArgumentException() {
        ProductCategory productCategoryThree = new ProductCategory(null, "Department", "Description");
        ProductCategory productCategoryFour = new ProductCategory("Name", null, "Description");
        ProductCategory productCategoryFive = new ProductCategory("Name", "Department", null);
        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.add(productCategoryThree));
        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.add(productCategoryFour));
        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.add(productCategoryFive));
    }

//    @Test
//    public void find_findFirstId_returnTestSupplierOne() {
//        Supplier supplier = supplierDao.find(1);
//        assertEquals(supplier, supplierOne);
//    }
//
//    @Test
//    public void find_findSecondId_returnTestSupplierTwo() {
//        Supplier supplier = supplierDao.find(2);
//        assertEquals(supplier, supplierTwo);
//    }
//
//    @Test
//    public void remove_removeFirstSupplier_getNull(){
//        supplierDao.remove(1);
//        Supplier supplier = supplierDao.find(1);
//        assertNull(supplier);
//    }
//
//    @Test
//    public void getAll_getAllSuppliers_hasLengthTwo() {
//        List<Supplier> suppliers = supplierDao.getAll();
//        assertEquals(suppliers.size(), 2);
//    }

}
