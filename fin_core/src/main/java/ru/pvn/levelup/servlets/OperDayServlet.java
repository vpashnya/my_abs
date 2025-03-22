package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.utils.CurrentBankInfoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/operday")
public class OperDayServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = CurrentBankInfoUtils.getBankInfo().getOperDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ObjectMapper mapper = new ObjectMapper();
        resp.getOutputStream().write(mapper.writeValueAsString(date).getBytes());
    }
}
