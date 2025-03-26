package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.utils.CashOperationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cashoperation")
public class CashOperationServlet extends HttpServlet {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CashOperation> cashOperationList =
                req.getParameterMap().containsKey("id")
                        ? List.of(CashOperationUtils.getCashOperationById(Integer.parseInt(req.getParameter("id"))))
                        : CashOperationUtils.getAllCashOperations();

        req.setAttribute("cashOperationList", cashOperationList);
        getServletContext().getRequestDispatcher("/WEB-INF/cashoperation.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CashOperation cashOperation = MAPPER.readValue(req.getInputStream().readAllBytes(), CashOperation.class);
        CashOperationUtils.saveAndExecute(cashOperation);
        resp.getOutputStream().write(MAPPER.writeValueAsString(cashOperation).getBytes());
    }

}
