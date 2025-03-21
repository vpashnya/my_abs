package ru.pvn.levelup.servlets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.entities.CashPoint;
import ru.pvn.levelup.utils.CashOperationUtils;
import ru.pvn.levelup.utils.CashPointUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/")
public class CashPointServlet extends HttpServlet {

    @AllArgsConstructor
    @Getter
    public class CashPointWithOperation {
        private CashPoint cashPoint;
        private Integer count;

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final List<CashPointWithOperation> cashPointList = new ArrayList<>();

        CashPointUtils
                .getAllCashPoints()
                .stream()
                .forEach(it->{cashPointList.add(new CashPointWithOperation(it, CashOperationUtils.getCountOperationsByCashPoint(it)));});

        req.setAttribute("cashPointList", cashPointList);
        getServletContext().getRequestDispatcher("/WEB-INF/cashpoint.jsp").forward(req, resp);
    }


}
