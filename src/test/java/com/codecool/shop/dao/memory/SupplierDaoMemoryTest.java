package com.codecool.shop.dao.memory;

import com.codecool.shop.dao.implementation_memory.SupplierDaoMem;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SupplierDaoMemoryTest {
    private static SupplierDao supplierDao;
    private static Supplier supplierOne;
    private static Supplier supplierTwo;

    @BeforeEach
    void setup() {
         supplierDao = SupplierDaoMem.getInstance();
         supplierOne = new Supplier("Test Supplier One", "Test Supplier One Description");
         supplierTwo = new Supplier("Test Supplier Two", "Test Supplier Two Description");
         supplierDao.add(supplierOne);
         supplierDao.add(supplierTwo);
    }

    @Test
    void add_addNewSupplier_doesNotThrowAnException() {
        assertDoesNotThrow(() -> supplierDao.add(supplierOne));
    }
}
