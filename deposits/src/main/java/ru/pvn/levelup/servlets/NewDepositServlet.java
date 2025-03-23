package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.utils.DepositUtils;

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
        req.setCharacterEncoding("UTF-8");

        DepositUtils.createDeposit(req.getParameterMap());

        getServletContext().getRequestDispatcher("/WEB-INF/depositcreate.jsp").forward(req, resp);
    }

}
