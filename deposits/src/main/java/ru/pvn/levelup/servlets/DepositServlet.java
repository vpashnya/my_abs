package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.utils.DepositUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Deposit> depositList = req.getParameterMap().containsKey("id")
                ? List.of(DepositUtils.getDepositById(Integer.parseInt(req.getParameter("id"))))
                : DepositUtils.getAllDeposits();


        depositList.stream().forEach(deposit -> {
            deposit.setAccountRest(DepositUtils.getRest(deposit));
        });

        req.setAttribute("depositList", depositList);
        getServletContext().getRequestDispatcher("/WEB-INF/deposit.jsp").forward(req, resp);
    }
}
