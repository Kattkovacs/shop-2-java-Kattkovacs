package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.interfaces.UserDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserDetails;
import com.codecool.shop.util.Email;
import com.codecool.shop.util.Password;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String jsonString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = gson.fromJson(jsonString, User.class);
        UserDao userDao = DaoSelector.getUserDataStore();
        try {
            userDao.add(user, Password.getSaltedHash(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Context contextEmail = new Context();
        StringWriter writer = new StringWriter();
        contextEmail.setVariable("name", user.getFirstName() + " " + user.getLastName());
        engine.process("email/welcome.html", contextEmail, writer);
        System.out.println(writer.toString());
        try {
            Email.sendEmail(user.getEmail(), writer.toString(), "Welcome to CodeCoolShop");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        context.setVariable("user", user);
        engine.process("successfulRegistration.html", context, resp.getWriter());
    }

    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            resp.setContentType("text/plain;charset=UTF-8");
            engine.process("registration.html", context, resp.getWriter());
        }
}
