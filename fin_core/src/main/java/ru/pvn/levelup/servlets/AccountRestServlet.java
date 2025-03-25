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
import java.rmi.RemoteException;

@WebServlet("/accountrest")
public class AccountRestServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account;
        if (req.getParameterMap().containsKey("id")) {
            account = AccountUtils.getAccoutById(Integer.parseInt(req.getParameter("id")));
        } else if (req.getParameterMap().containsKey("accnum")) {
            account = AccountUtils.getAccoutByNum(req.getParameter("accnum"));
        } else {
            throw new RuntimeException("Невозможно определить счет");
        }

        if (account == null) {
            throw new RemoteException("Невозможно определить счет");
        }

        account.setRest(FinRecordUtils.getRest(account));
        resp.getOutputStream().write(mapper.writeValueAsString(account).getBytes());
    }
}
