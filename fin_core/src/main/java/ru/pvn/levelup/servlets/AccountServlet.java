package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.utils.AccountUtils;
import ru.pvn.levelup.utils.FinRecordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private final ObjectMapper MAPPER = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accountList = req.getParameterMap().containsKey("id")
                ?
                List.of(AccountUtils.getAccoutById(Integer.parseInt(req.getParameter("id"))))
                :
                AccountUtils.getAllAccounts();

        accountList.stream().forEach(account -> {
            account.setRest(FinRecordUtils.getRest(account));
        });

        req.setAttribute("accountList", accountList);
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = MAPPER.readValue(req.getInputStream().readAllBytes(), Account.class);
        Account newAccount = AccountUtils.openInBalancePosition(account.getAccNum(), account.getClient());
        resp.getOutputStream().write(MAPPER.writeValueAsString(newAccount).getBytes());

    }

}
