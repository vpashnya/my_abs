package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.Client;
import ru.pvn.levelup.utils.ClientUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clientList = null;

        if (req.getParameterMap().containsKey("id")) {
            Client client = ClientUtils.getClientById(Integer.parseInt(req.getParameterMap().get("id")[0]));
            clientList = new ArrayList<>();
            clientList.add(client);

        } else {
            clientList = ClientUtils.getAllClient();
        }
        req.setAttribute("clientList", clientList);
        getServletContext().getRequestDispatcher("/WEB-INF/client.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        new BufferedReader(req.getReader()).transferTo(stringWriter);
        ObjectMapper mapper = new ObjectMapper();
        Client client = mapper.readValue(stringWriter.getBuffer().toString(), Client.class);
        ClientUtils.saveClient(client);
        resp.getWriter().println(mapper.writeValueAsString(client));

    }
}
