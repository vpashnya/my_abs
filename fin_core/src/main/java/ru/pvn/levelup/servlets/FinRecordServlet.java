package ru.pvn.levelup.servlets;

import ru.pvn.levelup.entities.FinRecord;
import ru.pvn.levelup.utils.FinRecordUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/finrecord")
public class FinRecordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FinRecord> recordList ;
        if (req.getParameterMap().containsKey("id")) {
            FinRecord record = FinRecordUtils.getFinRecordById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            recordList = new ArrayList<>();
            recordList.add(record);

        } else {
            recordList = FinRecordUtils.getAllFinRecords();
        }

        req.setAttribute("recList", recordList);
        getServletContext().getRequestDispatcher("/WEB-INF/finrecs.jsp").forward(req, resp);

    }
}
