package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.entities.DepositOperation;
import ru.pvn.levelup.utils.DepositOperationUtils;
import ru.pvn.levelup.utils.DepositUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/depositoperation")
public class DepositOperationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<DepositOperation> depOperList = new ArrayList<>();

        if (req.getParameterMap().containsKey("id")) {
            DepositOperation operation = DepositOperationUtils.getDepositOperationById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            depOperList.add(operation);

        } else {
            depOperList.addAll(DepositOperationUtils.getAllDepositOperation());
        }

        req.setAttribute("depOperList", depOperList);
        getServletContext().getRequestDispatcher("/WEB-INF/depositoperations.jsp").forward(req, resp);
    }
}
