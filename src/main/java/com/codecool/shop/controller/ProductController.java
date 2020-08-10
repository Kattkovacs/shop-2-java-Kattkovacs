package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Object filterBy = getFilterBy(
                req.getParameter("categoryId"),
                req.getParameter("supplierId")
        );
        List<Product> products = getProductList(filterBy);

        context.setVariable("products", products);
        context.setVariable("filterBy", filterBy);

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        // // Alternative setting of the template context
//        Map<String, Object> params = new HashMap<>();
//        params.put("products", products);
//        params.put("filterBy", filterBy);
//        params.put("categories", productCategoryDataStore.getAll());
//        params.put("suppliers", supplierDataStore.getAll());
//        context.setVariables(params);

        engine.process("product/index.html", context, resp.getWriter());
    }

    private Object getFilterBy(String categoryId, String supplierId) {

        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        int categoryIdNum = castStringToInt(categoryId);
        int supplierIdNum = castStringToInt(supplierId);
        if (categoryIdNum != 0) return productCategoryDataStore.find(categoryIdNum);
        if (supplierIdNum != 0) return supplierDataStore.find(supplierIdNum);
        return productCategoryDataStore.find(1);
    }


    private List<Product> getProductList(Object selectedCategory) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        if (selectedCategory instanceof ProductCategory) {
            return productDataStore.getBy((ProductCategory) selectedCategory);
        } else if (selectedCategory instanceof Supplier) {
            return productDataStore.getBy((Supplier) selectedCategory);
        }
        return null;
    }

    private int castStringToInt(String text) {
        int number = 0;
        try {
            number = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Can't parse " + text + " to number");
        }
        return number;
    }

}
