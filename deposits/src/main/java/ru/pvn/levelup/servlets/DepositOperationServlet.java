package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.DepositOperation;
import ru.pvn.levelup.utils.DepositOperationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/depositoperation")
public class DepositOperationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DepositOperation> depOperList =
                req.getParameterMap().containsKey("id")
                        ?
                        List.of(DepositOperationUtils.getDepositOperationById(Integer.parseInt(req.getParameter("id"))))
                        :
                        DepositOperationUtils.getAllDepositOperation();


        req.setAttribute("depOperList", depOperList);
        getServletContext().getRequestDispatcher("/WEB-INF/depositoperations.jsp").forward(req, resp);
    }
}
