package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.Client;
import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.utils.DepositUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@WebServlet("/")
public class NewDepositServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/newdeposit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameterMap().entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue()[0]);
        });

        Map<String, String[]> params = req.getParameterMap();

        Client client = DepositUtils.getClient(params.get("inn")[0], params.get("name")[0]);
        Account account = DepositUtils.getAccount(Integer.parseInt(params.get("duration")[0]), client);
        LocalDate operDay = DepositUtils.getOperDay();

        Deposit deposit = new Deposit(null
                , client.getId()
                , client.getFullName()
                , account.getId()
                , account.getAccNum()
                , new BigDecimal(params.get("sum")[0])
                , new BigDecimal(params.get("prc")[0].replace("%", " ").trim())
                , Integer.parseInt(params.get("duration")[0])
                , operDay
                , Deposit.Status.NEW
                , switch (params.get("payto")[0]) {
                            case "cash" -> Deposit.PayTo.TO_CASH;
                            default -> Deposit.PayTo.IN_BANK;    }
                , null);

        DepositUtils.saveDeposit(deposit);

        getServletContext().getRequestDispatcher("/WEB-INF/newdeposit.jsp").forward(req, resp);
    }

}
