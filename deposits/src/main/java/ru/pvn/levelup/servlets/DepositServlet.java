package ru.pvn.levelup.servlets;


import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.utils.DepositUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Deposit> depositList = new ArrayList<>();

        if (req.getParameterMap().containsKey("id")) {
            Deposit deposit = DepositUtils.getDepositById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            depositList.add(deposit);

        } else {
            depositList.addAll(DepositUtils.getAllDeposits());
        }

        depositList.stream().forEach(deposit -> {
            deposit.setAccountRest(DepositUtils.getRest(deposit));
        });

        req.setAttribute("depositList", depositList);
        getServletContext().getRequestDispatcher("/WEB-INF/deposit.jsp").forward(req, resp);
    }
}
