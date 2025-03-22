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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/paydocument")
public class PayDocumentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PayDocument> payDocs = null;

        if (req.getParameterMap().containsKey("id")) {
            PayDocument payDoc = PayDocumentUtils.getPayDocumentById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            payDocs = new ArrayList<>();
            payDocs.add(payDoc);
        } else {
            payDocs = PayDocumentUtils.getAllPayDocuments();
        }
        req.setAttribute("payDocsList", payDocs);
        getServletContext().getRequestDispatcher("/WEB-INF/paydocs.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody  = new String(req.getInputStream().readAllBytes()) ;
        ObjectMapper mapper = new ObjectMapper();
        PayDocument document = mapper.readValue(reqBody, PayDocument.class);
        PayDocumentUtils.savePayDocument(document);
        PayDocumentUtils.executePayDocument(document);
        resp.getOutputStream().write(mapper.writeValueAsString(document).getBytes());
    }
}
