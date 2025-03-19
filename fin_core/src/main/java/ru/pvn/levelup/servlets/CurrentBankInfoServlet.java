package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.CurrentBankInfo;
import ru.pvn.levelup.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class CurrentBankInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getCurrentBankInfoJsp(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentBankInfoUtils.switch2NextDay();
        getCurrentBankInfoJsp(req, resp);

    }

    private void getCurrentBankInfoJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentBankInfo bankInfo = CurrentBankInfoUtils.getBankInfo();

        req.setAttribute("bankName", bankInfo.getBankClient().getFullName());
        req.setAttribute("bankCode", bankInfo.getBankCode());
        req.setAttribute("bankCorAcc", bankInfo.getCorrAccount().getAccNum());
        req.setAttribute("bankOperDay", bankInfo.getOperDay());

        req.setAttribute("clientsCount", ClientUtils.getClientCount());
        req.setAttribute("accountsCount", AccountUtils.getAccountCount());
        req.setAttribute("payDocsCount", PayDocumentUtils.getPayDocsCount());
        req.setAttribute("finRecordsCount", FinRecordUtils.getFinRecordsCount());

        getServletContext().getRequestDispatcher("/WEB-INF/current_bank_info.jsp").forward(req, resp);

    }

}
