package ru.pvn.levelup.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class NewDepositServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/newdeposit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameterMap().entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        });

        getServletContext().getRequestDispatcher("/WEB-INF/newdeposit.jsp").forward(req, resp);
    }

}
