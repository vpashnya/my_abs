package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.utils.CashOperationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cashoperation")
public class CashOperationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CashOperation> cashOperationList;

        if (req.getParameterMap().containsKey("id")) {
            CashOperation cashOperation = CashOperationUtils.getCashOperationById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            cashOperationList = new ArrayList<>();
            cashOperationList.add(cashOperation);
        } else {
            cashOperationList = CashOperationUtils.getAllCashOperations();
        }
        req.setAttribute("cashOperationList", cashOperationList);
        getServletContext().getRequestDispatcher("/WEB-INF/cashoperation.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        new BufferedReader(req.getReader()).transferTo(stringWriter);
        ObjectMapper mapper = new ObjectMapper();

        CashOperation cashOperation = mapper.readValue(stringWriter.getBuffer().toString(), CashOperation.class);
        CashOperationUtils.saveAndExecute(cashOperation);

        resp.getWriter().println(mapper.writeValueAsString(cashOperation));

    }

}
