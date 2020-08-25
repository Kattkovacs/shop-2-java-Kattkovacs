package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Gson gson = new Gson();
//        String jsonString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        UserDetails userDetails = gson.fromJson(jsonString, UserDetails.class);
//
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("userDetails", userDetails);
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        engine.process("registration.html", context, resp.getWriter());
//    }

    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            resp.setContentType("text/plain;charset=UTF-8");
            engine.process("registration.html", context, resp.getWriter());
        }
}
