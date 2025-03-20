package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.CashPoint;
import ru.pvn.levelup.utils.CashPointUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class CashPointServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CashPoint> cashPointList;

        if (req.getParameterMap().containsKey("id")) {
            CashPoint cashPoint = CashPointUtils.getCashPointById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            cashPointList = new ArrayList<>();
            cashPointList.add(cashPoint);
        } else {
            cashPointList = CashPointUtils.getAllCashPoints();
        }
        req.setAttribute("cashPointList", cashPointList);
        getServletContext().getRequestDispatcher("/WEB-INF/cashpoint.jsp").forward(req, resp);
    }
}
