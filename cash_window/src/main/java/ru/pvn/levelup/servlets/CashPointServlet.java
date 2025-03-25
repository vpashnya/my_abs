package ru.pvn.levelup.servlets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.pvn.levelup.entities.CashPoint;
import ru.pvn.levelup.utils.CashOperationUtils;
import ru.pvn.levelup.utils.CashPointUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
        List<CashPointWithOperation> cashPointList = CashPointUtils
                .getAllCashPoints()
                .stream()
                .map((cashPoint) -> {
                    return new CashPointWithOperation(cashPoint, CashOperationUtils.getCountOperationsByCashPoint(cashPoint));
                })
                .collect(Collectors.toList());


        req.setAttribute("cashPointList", cashPointList);
        getServletContext().getRequestDispatcher("/WEB-INF/cashpoint.jsp").forward(req, resp);
    }


}
