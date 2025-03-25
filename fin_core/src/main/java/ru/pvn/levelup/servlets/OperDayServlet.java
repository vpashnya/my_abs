package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.abscore.Constants;
import ru.pvn.levelup.utils.CurrentBankInfoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/operday")
public class OperDayServlet extends HttpServlet {
    private final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = CurrentBankInfoUtils.getBankInfo().getOperDay().format(Constants.INTEGRAION_DATE_FORMAT);
        resp.getOutputStream().write(MAPPER.writeValueAsString(date).getBytes());
    }
}
