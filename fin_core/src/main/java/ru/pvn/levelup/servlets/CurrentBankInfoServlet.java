package ru.pvn.levelup.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.pvn.levelup.utils.CurrentBankInfoUtils.*;
import static ru.pvn.levelup.utils.ClientUtils.*;
import static ru.pvn.levelup.utils.AccountUtils.*;
import static ru.pvn.levelup.utils.PayDocumentUtils.*;
import static ru.pvn.levelup.utils.FinRecordUtils.*;

@WebServlet("/")
public class CurrentBankInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getCurrentBankInfoJsp(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch2NextDay();
        getCurrentBankInfoJsp(req, resp);

    }

    private void getCurrentBankInfoJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("bankName", getBankInfo().getBankClient().getFullName());
        req.setAttribute("bankCode", getBankInfo().getBankCode());
        req.setAttribute("bankCorAcc", getBankInfo().getCorrAccount().getAccNum());
        req.setAttribute("bankOperDay", getBankInfo().getOperDay());

        req.setAttribute("clientsCount", getClientCount());
        req.setAttribute("accountsCount", getAccountCount());
        req.setAttribute("payDocsCount", getPayDocsCount());
        req.setAttribute("finRecordsCount", getFinRecordsCount());

        getServletContext().getRequestDispatcher("/WEB-INF/current_bank_info.jsp").forward(req, resp);

    }

}
