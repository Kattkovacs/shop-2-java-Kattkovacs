package com.codecool.shop.controller;

import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.dao.interfaces.ProductDao;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.config.TemplateEngineUtil;
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

    SupplierDao supplierDataStore = DaoSelector.getSupplierDataStore();
    ProductCategoryDao productCategoryDataStore = DaoSelector.getProductCategoryDataStore();
    ProductDao productDataStore = DaoSelector.getProductDataStore();
    OrderDao orderCacheStore = DaoSelector.getOrderCacheStore();

    List<Product> products;
    String filterName;
    String filterDescription;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String categoryId = req.getParameter("categoryId");
        String supplierId = req.getParameter("supplierId");

        if (supplierId != null) {
            setReturningDataFilteredBySupplier(supplierId);
        } else if (categoryId != null) {
            setReturningDataFilteredByCategory(categoryId);
        } else {
            setReturningDataFilteredByCategory("1");
        }

        setContextVariables(req, context);

        engine.process("product/index.html", context, resp.getWriter());
    }

    private void setContextVariables(HttpServletRequest req, WebContext context) {
        context.setVariable("products", products);
        context.setVariable("filterName", filterName);
        context.setVariable("filterDescription", filterDescription);
        context.setVariable("order", orderCacheStore.find(req.getSession().getId()));
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("page", "products");
    }

    private void setReturningDataFilteredByCategory(String categoryId) {
        ProductCategory productCategory = productCategoryDataStore.find(castStringToInt(categoryId));
        products = productDataStore.getBy(productCategory);
        setFilterInfo(productCategory.getName(), productCategory.getDescription());
    }

    private void setReturningDataFilteredBySupplier(String supplierId) {
        Supplier supplier = supplierDataStore.find(castStringToInt(supplierId));
        products = productDataStore.getBy(supplier);
        setFilterInfo(supplier.getName(), supplier.getDescription());
    }

    private void setFilterInfo(String filterName, String filterDescription) {
        this.filterName = filterName;
        this.filterDescription = filterDescription;
    }

    private int castStringToInt(String text) {
        if (text == null) return 1;
        int number = 1;
        try {
            number = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Can't parse " + text + " to number");
        }
        return number;
    }

}
