package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation_JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation_memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation_memory.ProductDaoMem;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.dao.implementation_memory.OrderDaoMem;
import com.codecool.shop.dao.interfaces.ProductCategoryDao;
import com.codecool.shop.dao.interfaces.ProductDao;
import com.codecool.shop.dao.interfaces.SupplierDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.UserDetails;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    OrderDao orderDataStore = DaoSelector.getOrderDataStore();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Order order = orderDataStore.find(req.getSession().getId());
        UserDetails userDetails = order.getUserDetails();
        context.setVariable("userDetails", userDetails);
        engine.process("success.html", context, resp.getWriter());
        orderDataStore.remove(req.getSession().getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Order order = orderDataStore.find(req.getSession().getId());
        context.setVariable("order", order);
        resp.setContentType("text/plain;charset=UTF-8");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        engine.process("payment.html", context, resp.getWriter());
    }
}
