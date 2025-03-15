package ru.pvn.dao.fincore;

import ru.pvn.dao.AbstractAbsDao;
import ru.pvn.dbhelpers.DBHelperFinCore;
import ru.pvn.objclasses.fincore.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ClientDaoImpl extends AbstractAbsDao<Client> {
    private static ClientDaoImpl currentDao = new ClientDaoImpl();

    private ClientDaoImpl() {
        super(Client.class, DBHelperFinCore.getEntityManager());
    }

    public static ClientDaoImpl getDao() {
        return currentDao;
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
            Client client = new Client(id, name, inn);
            save(client);
            return client;
        }
    }

}
