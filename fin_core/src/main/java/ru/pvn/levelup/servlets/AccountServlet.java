package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.utils.AccountUtils;
import ru.pvn.levelup.utils.FinRecordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    @AllArgsConstructor
    @Getter
    public class AccountWithRest{
           Account account;
           BigDecimal rest;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        final List<AccountWithRest> accountList = new ArrayList<>();

        if (req.getParameterMap().containsKey("id")) {
            Account account = AccountUtils.getAccoutById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            accountList.add(new AccountWithRest(account, FinRecordUtils.getRest(account)));

        } else {
            AccountUtils.getAllAccounts().stream().forEach(account -> {accountList.add(new AccountWithRest(account, FinRecordUtils.getRest(account)));});
        }
        req.setAttribute("accountList", accountList);
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        new BufferedReader(req.getReader()).transferTo(stringWriter);
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(stringWriter.getBuffer().toString(), Account.class);
        Account newAccount = AccountUtils.openInBalancePosition(account.getAccNum(), account.getClient());
        resp.getWriter().println(mapper.writeValueAsString(newAccount));

    }

}
