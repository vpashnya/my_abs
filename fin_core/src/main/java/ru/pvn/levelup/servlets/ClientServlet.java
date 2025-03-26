package ru.pvn.levelup.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.Client;
import ru.pvn.levelup.utils.ClientUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    private final ObjectMapper MAPPER = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clientList = req.getParameterMap().containsKey("id")
                ? List.of(ClientUtils.getClientById(Integer.parseInt(req.getParameter("id"))))
                : ClientUtils.getAllClient();

        req.setAttribute("clientList", clientList);
        getServletContext().getRequestDispatcher("/WEB-INF/client.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = MAPPER.readValue(req.getInputStream().readAllBytes(), Client.class);
        Client chkClient = ClientUtils.getClientByParams(client.getId(), client.getFullName(), client.getInn());
        resp.getOutputStream().write(MAPPER.writeValueAsString(chkClient).getBytes());

    }
}
