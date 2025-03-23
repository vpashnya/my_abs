package ru.pvn.levelup.servlets;

import ru.pvn.levelup.utils.EndDayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/endday")
public class EndDayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EndDayUtils.sendAllCash2CorAccount();
        resp.getWriter().println("Day ended in cash window !");
    }
}
