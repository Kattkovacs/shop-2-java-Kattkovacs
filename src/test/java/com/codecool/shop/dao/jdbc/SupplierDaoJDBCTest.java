package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.implementation_JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierDaoJDBCTest {
    public static final String CONNECTION_TEST_PROPERTIES = "connection_test.properties";
    private static SupplierDao supplierDao;
    private static Supplier supplierOne;
    private static Supplier supplierTwo;


    @BeforeEach
    void setup() {
        supplierDao = SupplierDaoJDBC.getInstance(CONNECTION_TEST_PROPERTIES);
        supplierOne = new Supplier("Test Supplier One", "Test Supplier One Description");
        supplierTwo = new Supplier("Test Supplier Two", "Test Supplier Two Description");
    }

    @Test
    public void add_addSupplierWithNullArgument_throwIllegalArgumentException() {
        Supplier supplierThree = new Supplier(null, "Description");
        Supplier supplierFour = new Supplier("Name", null);
        assertThrows(IllegalArgumentException.class, () -> supplierDao.add(supplierThree));
        assertThrows(IllegalArgumentException.class, () -> supplierDao.add(supplierFour));
    }

    @AfterAll
    static void resetDatabase() {
        DataSource dataSource = ShopDatabaseManager.getInstance(CONNECTION_TEST_PROPERTIES).getDataSource();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS supplier_pk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS product_category_pk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS supplier_id_fk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_category_id_fk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_pk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.user DROP CONSTRAINT IF EXISTS user_pk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.order DROP CONSTRAINT IF EXISTS order_pk CASCADE;\n" +
                    "ALTER TABLE IF EXISTS ONLY public.order_details DROP CONSTRAINT IF EXISTS order_details_pk CASCADE;\n" +
                    "\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS public.supplier;\n" +
                    "CREATE TABLE public.supplier (\n" +
                    "                                id serial NOT NULL\n" +
                    "                                   constraint supplier_pk\n" +
                    "                                       primary key,\n" +
                    "                                name text NOT NULL,\n" +
                    "                                description text NOT NULL\n" +
                    "                             );\n" +
                    "\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS public.product_category;\n" +
                    "CREATE TABLE public.product_category (\n" +
                    "                                 id serial NOT NULL\n" +
                    "                                     constraint product_category_pk\n" +
                    "                                         primary key,\n" +
                    "                                 name text NOT NULL,\n" +
                    "                                 description text NOT NULL,\n" +
                    "                                 department text NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS public.product;\n" +
                    "CREATE TABLE public.product (\n" +
                    "                                 id serial NOT NULL\n" +
                    "                                     constraint product_pk\n" +
                    "                                         primary key,\n" +
                    "                                 name text NOT NULL,\n" +
                    "                                 description text NOT NULL,\n" +
                    "                                 default_price float8 NOT NULL,\n" +
                    "                                 default_currency text NOT NULL,\n" +
                    "                                 supplier_id integer NOT NULL\n" +
                    "                                     constraint supplier_id_fk\n" +
                    "                                         references supplier\n" +
                    "                                            on delete cascade,\n" +
                    "                                 product_category_id integer NOT NULL\n" +
                    "                                     constraint product_category_id_fk\n" +
                    "                                         references product_category\n" +
                    "                                            on delete cascade\n" +
                    ");\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS public.user;\n" +
                    "CREATE TABLE public.user (\n" +
                    "                                id serial NOT NULL\n" +
                    "                                   constraint user_pk\n" +
                    "                                       primary key,\n" +
                    "                                first_name text NOT NULL,\n" +
                    "                                last_name text NOT NULL,\n" +
                    "                                email text NOT NULL unique,\n" +
                    "                                password text NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS public.order;\n" +
                    "CREATE TABLE public.order (\n" +
                    "                              id serial NOT NULL\n" +
                    "                                  constraint order_pk\n" +
                    "                                      primary key,\n" +
                    "                              user_id integer\n" +
                    "                                  constraint user_id_fk\n" +
                    "                                      references \"user\"\n" +
                    "                                      on delete cascade,\n" +
                    "                              total_order_price float8 NOT NULL,\n" +
                    "                              first_name text NOT NULL,\n" +
                    "                              last_name text NOT NULL,\n" +
                    "                              email text NOT NULL,\n" +
                    "                              phone text NOT NULL,\n" +
                    "                              address text NOT NULL,\n" +
                    "                              city text NOT NULL,\n" +
                    "                              state text NOT NULL,\n" +
                    "                              zip int NOT NULL,\n" +
                    "                              address2 text NOT NULL,\n" +
                    "                              city2 text NOT NULL,\n" +
                    "                              state2 text NOT NULL,\n" +
                    "                              zip2 int NOT NULL\n" +
                    "\n" +
                    ");\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS public.order_details;\n" +
                    "CREATE TABLE public.order_details (\n" +
                    "                                id serial NOT NULL\n" +
                    "                                   constraint order_details_pk\n" +
                    "                                       primary key,\n" +
                    "                                order_id integer NOT NULL\n" +
                    "                                    constraint order_id_fk\n" +
                    "                                        references \"order\"\n" +
                    "                                            on delete cascade,\n" +
                    "                                product_id integer NOT NULL\n" +
                    "                                    constraint product_id_fk\n" +
                    "                                        references product\n" +
                    "                                            on delete cascade,\n" +
                    "                                quantity integer NOT NULL,\n" +
                    "                                total_product_price float8 NOT NULL\n" +
                    ");";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading db: " + e);
        }
    }

}
