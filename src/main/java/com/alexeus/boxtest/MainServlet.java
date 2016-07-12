package com.alexeus.boxtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для обработки
 * @author akiryanov
 * @since 02.04.13
 */
@WebServlet("/boxresponse/*")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        if (error != null) {
            logger.info("Failed: " + error + " " + req.getParameter("error_description"));
            ResponseHolder.getResponseHolder().addError(error, req.getParameter("error_description"));
            resp.getWriter().append("Failed: ").append(error).append(" ").append(req.getParameter("error_description")).flush();
        } else {
            logger.info("Success: " + req.getParameter("code") + " " + req.getParameter("state"));
            ResponseHolder.getResponseHolder().addSuccess(req.getParameter("code"), req.getParameter("state"));
            resp.getWriter().append("Success: ").append(req.getParameter("code")).append(" ").append(req.getParameter("state")).flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
