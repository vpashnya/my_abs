package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.PayDocument;
import ru.pvn.levelup.utils.PayDocumentUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/paydocument")
public class PayDocumentServlet extends HttpServlet {
    private final ObjectMapper MAPPER = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PayDocument> payDocs = req.getParameterMap().containsKey("id")
                ?
                List.of(PayDocumentUtils.getPayDocumentById(Integer.parseInt(req.getParameter("id"))))
                :
                PayDocumentUtils.getAllPayDocuments();

        req.setAttribute("payDocsList", payDocs);
        getServletContext().getRequestDispatcher("/WEB-INF/paydocs.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PayDocument document = MAPPER.readValue(req.getInputStream().readAllBytes(), PayDocument.class);
        PayDocumentUtils.savePayDocument(document);
        PayDocumentUtils.executePayDocument(document);
        resp.getOutputStream().write(MAPPER.writeValueAsString(document).getBytes());
    }
}
