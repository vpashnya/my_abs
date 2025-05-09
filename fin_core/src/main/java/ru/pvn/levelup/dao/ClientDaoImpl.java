package ru.pvn.levelup.dao;

import lombok.Getter;
import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperFinCore;
import ru.pvn.levelup.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ClientDaoImpl extends AbstractAbsDao<Client> {
    @Getter
    private static ClientDaoImpl currentDao = new ClientDaoImpl();

    private ClientDaoImpl() {
        super(Client.class, DBHelperFinCore.getEntityManager());
    }

    public Client findOrCreate(Integer id, String name, String inn) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Client where id = ?1 or inn = ?2");
        query.setParameter(1, id);
        query.setParameter(2, inn);
        List<Client> clients = query.getResultList();
        entityManager.getTransaction().commit();
        if (!clients.isEmpty()) {
            return clients.get(0);
        } else {
            Client client = new Client( name, inn);
            save(client);
            return client;
        }
    }

}
