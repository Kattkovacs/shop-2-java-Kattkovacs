package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.dao.interfaces.ProductDao;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.json.LineItemUpdatesJson;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    SupplierDao supplierDataStore = DaoSelector.getSupplierDataStore();
    ProductCategoryDao productCategoryDataStore = DaoSelector.getProductCategoryDataStore();
    ProductDao productDataStore = DaoSelector.getProductDataStore();
    OrderDao orderDataStore = DaoSelector.getOrderDataStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order order = orderDataStore.find(req.getSession().getId());

        if (req.getParameter("add") != null) {
            order.addToCart(new LineItem(
                    productDataStore.find(Integer.parseInt(req.getParameter("add"))),
                    order
            ));
        }

        if (req.getParameter("increment") != null) {
            LineItem lineItem = order.getLineItem(Integer.parseInt(req.getParameter("increment")));
            lineItem.incrementQuantity();
            this.sendOrderUpdates(resp, order, lineItem);
            return;
        }

        if (req.getParameter("decrement") != null) {
            LineItem lineItem = order.getLineItem(Integer.parseInt(req.getParameter("decrement")));
            lineItem.decrementQuantity();
            this.sendOrderUpdates(resp, order, lineItem);
            return;
        }

        if (req.getParameter("remove") != null) {
            LineItem lineItem = order.getLineItem(Integer.parseInt(req.getParameter("remove")));
            lineItem.setQuantity(0);
            order.removeFromCart(Integer.parseInt(req.getParameter("remove")));
            this.sendOrderUpdates(resp, order, lineItem);
            return;
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("order", orderDataStore.find(req.getSession().getId()));
        context.setVariable("page", "cart");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    private void sendOrderUpdates(HttpServletResponse resp, Order order, LineItem lineItem) throws IOException {
        String responseString = new Gson().toJson(new LineItemUpdatesJson(lineItem, order));
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
    }
}
