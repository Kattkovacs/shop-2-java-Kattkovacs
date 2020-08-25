package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.interfaces.OrderDao;
import com.codecool.shop.dao.implementation_memory.OrderDaoMem;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        UserDetails userDetails = gson.fromJson(jsonString, UserDetails.class);
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Order order = orderDataStore.find(req.getSession().getId());
        order.setUserDetails(userDetails);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("userDetails", userDetails);
        context.setVariable("order", order);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        engine.process("review.html", context, resp.getWriter());
    }

    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            resp.setContentType("text/plain;charset=UTF-8");
            engine.process("user_form.html", context, resp.getWriter());
        }
}
