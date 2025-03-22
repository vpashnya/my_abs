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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Account> accountList = new ArrayList<>();

        if (req.getParameterMap().containsKey("id")) {
            Account account = AccountUtils.getAccoutById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            accountList.add(account);

        } else {
            accountList.addAll(AccountUtils.getAllAccounts());
        }

        accountList.stream().forEach(account -> {
            account.setRest(FinRecordUtils.getRest(account));
        });

        req.setAttribute("accountList", accountList);
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody  = new String(req.getInputStream().readAllBytes()) ;
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(reqBody, Account.class);
        Account newAccount = AccountUtils.openInBalancePosition(account.getAccNum(), account.getClient());
        resp.getOutputStream().write(mapper.writeValueAsString(newAccount).getBytes());

    }

}
