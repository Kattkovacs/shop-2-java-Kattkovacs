package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.interfaces.UserDao;
import com.codecool.shop.model.Login;
import com.codecool.shop.model.User;
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

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        UserDao userDao = DaoSelector.getUserDataStore();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String jsonString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Login login = gson.fromJson(jsonString, Login.class);
        User user = userDao.find(login.getEmail());
        if (user == null) {
            engine.process("loginFail.html", context, resp.getWriter());
            return;
        }
        try {
            if (!Password.check(login.getPassword(), user.getPassword())) {
                Context contextEmail = new Context();
                contextEmail.setVariable("user", user);
                StringWriter writer = new StringWriter();
                engine.process("email/invalidLogin.html", contextEmail, writer);
                try {
                    Email.sendEmail(user.getEmail(), writer.toString(), "Invalid login notification");
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                engine.process("loginFail.html", context, resp.getWriter());
                return;
            };
        } catch (Exception e) {
            engine.process("loginFail.html", context, resp.getWriter());
            e.printStackTrace();
            return;
        }
        context.setVariable("user", user);
        engine.process("loginSuccessful.html", context, resp.getWriter());
    }

    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            resp.setContentType("text/plain;charset=UTF-8");
            engine.process("login.html", context, resp.getWriter());
        }
}
