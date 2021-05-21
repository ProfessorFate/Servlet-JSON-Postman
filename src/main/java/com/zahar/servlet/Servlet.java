package com.zahar.servlet;


import com.zahar.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet("/data")
public class Servlet extends HttpServlet {
    private final Service service;
    private final static Logger LOG = Logger.getLogger(Servlet.class.getName());

    public Servlet() {
        service = new Service();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(service.getAllUsersForResponse());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        service.addUser(body);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            service.deleteUser(id);
        } catch (NumberFormatException e) {
            LOG.warning(e.getMessage());
            resp.setStatus(400);
        } catch (NoSuchElementException ex) {
            LOG.warning(ex.getMessage());
            resp.setStatus(404);
        }
    }
}