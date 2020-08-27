package com.codecool.shop.dao.memory;

import com.codecool.shop.dao.implementation_memory.SupplierDaoMem;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
         supplierOne.setId(1);
         supplierTwo.setId(2);
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
        Supplier supplier = supplierDao.find(1);
        assertEquals(supplierOne.getId(), supplier.getId());
    }

    @Test
    public void remove_removeFirstSupplier_getNull(){
        supplierDao.remove(1);
        Supplier supplier = supplierDao.find(1);
        assertNull(supplier);
    }

    @Test
    public void getAll_getAllSuppliers_hasLengthTwo() {
        List<Supplier> suppliers = supplierDao.getAll();
        assertEquals( 1, suppliers.size());
    }

}
