package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.ClientDaoImpl;
import ru.pvn.levelup.entities.Client;

import java.util.List;

@UtilityClass
public class ClientUtils {
    private ClientDaoImpl clientDao = ClientDaoImpl.getDao();

    public void saveClient(Client client) {
        clientDao.save(client);
    }

    public Client getClientByParams(Integer id, String fullName, String inn) {
        return clientDao.findOrCreate(id, fullName, inn);
    }

    public Integer getClientCount() {
        return clientDao.count();
    }

    public List<Client> getAllClient() {
        return clientDao.getAll();
    }

    public Client getClientById(int id) {
        return clientDao.get(id);
    }

}
