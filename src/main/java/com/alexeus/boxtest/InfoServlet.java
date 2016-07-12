package com.alexeus.boxtest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Сервлет для обработки
 * @author akiryanov
 * @since 02.04.13
 */
@WebServlet("/getinfo/*")
public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Success codes:");
        for (Map.Entry<String, String> entry: ResponseHolder.getResponseHolder().getSuccess().entrySet()) {
            resp.getWriter().append(entry.getKey()).append(" ").append(entry.getValue()).println();
        }

        resp.getWriter().println("\nError codes:");
        for (String entry: ResponseHolder.getResponseHolder().getError()) {
            resp.getWriter().println(entry);
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
