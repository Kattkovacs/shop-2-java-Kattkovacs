package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier nescafe = new Supplier("Nescafe", "For the moments that matter.");
        supplierDataStore.add(nescafe);
        Supplier tchibo = new Supplier("Tchibo", "A really good coffee.");
        supplierDataStore.add(tchibo);
        Supplier java = new Supplier("Java", "Write once, run anywhere :)");
        supplierDataStore.add(java);
        Supplier starbucks = new Supplier("Starbucks", "Get together. Over coffee.");
        supplierDataStore.add(starbucks);

        //setting up a new product category
        ProductCategory icedCoffee = new ProductCategory("Iced Coffee", "Coffee", "Iced coffees become very popular in the summer time due to its ice cubes in it :)");
        ProductCategory espresso = new ProductCategory("Espresso", "Coffee", "Also known as a short black, is apr. one ounce of highly concentrated coffee.");
        ProductCategory macchiato = new ProductCategory("Macchiato", "Coffee", "The word macchiato means mark or stain. This is in reference to the mark that steamed milk leaves on the surface of the espresso as it is dashed into the drink.");
        ProductCategory cappuccino = new ProductCategory("Cappuccino", "Coffee", "A creamy coffee drink is with thick foam layer and additional flavorings that can be added to it.");
        ProductCategory latte = new ProductCategory("Latte", "Coffee", "Cafe lattes are considered to be a coffee with a big amount of milk in the beverage. Flavoring syrups are added to the latte for those who enjoy sweeter drinks.");
        productCategoryDataStore.add(icedCoffee);
        productCategoryDataStore.add(espresso);
        productCategoryDataStore.add(macchiato);
        productCategoryDataStore.add(cappuccino);
        productCategoryDataStore.add(latte);

        //setting up products and printing it
        productDataStore.add(new Product("Ice Cold Cappuccino", 9.9f, "USD", "An ice cold cappuccino developed by our developer team.", icedCoffee, java));
        productDataStore.add(new Product("Amazon Fire", 4.9f, "USD", "Fantastic reboot to your brain. Large content coffeine. Helpful technical support.", espresso, nescafe));
        productDataStore.add(new Product("Codecoolers dream", 5.0f, "USD", "A sweet, bug safe drink to take a break.", cappuccino, tchibo));
        productDataStore.add(new Product("Go To Cheers", 8.0f, "USD", "Take a break.", latte, tchibo ));
        productDataStore.add(new Product("The cold roof", 5.9f, "USD", "When you believe compiler ignores all your comments.", icedCoffee, tchibo ));
        productDataStore.add(new Product("PA vulcano", 6.0f, "USD", "When you really need the energy and the power.", macchiato, starbucks ));
        productDataStore.add(new Product("Consultation friend", 8.0f, "USD", "A cold and sweet friend always.", icedCoffee, nescafe ));
        productDataStore.add(new Product("JavaCode", 8.0f, "USD", "This is the java, if you don't know it, then google!", cappuccino, starbucks ));
        productDataStore.add(new Product("Mentoring session", 6.0f, "USD", "A good coffee to develop in knowledge.", latte, nescafe ));
        productDataStore.add(new Product("Demo break", 4.9f, "USD", "When you only have 5 minutes..:)", espresso, java ));
    }
}
