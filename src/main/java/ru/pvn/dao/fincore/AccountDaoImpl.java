package ru.pvn.dao.fincore;

import ru.pvn.dao.AbstractAbsDao;
import ru.pvn.dbhelpers.DBHelperFinCore;
import ru.pvn.objclasses.fincore.Account;
import ru.pvn.objclasses.fincore.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AccountDaoImpl extends AbstractAbsDao<Account> {
    private static AccountDaoImpl currentDao = new AccountDaoImpl();

    private AccountDaoImpl() {
        super(Account.class, DBHelperFinCore.getEntityManager());
    }

    public static AccountDaoImpl getDao() {
        return currentDao;
    }

    public Account findOrCreate(Integer id, Account.AccType accType, String accNum, Client client) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Account where id = ?1 or acc_num = ?2");
        query.setParameter(1, id);
        query.setParameter(2, accNum);
        List<Account> accounts = query.getResultList();
        entityManager.getTransaction().commit();
        if (!accounts.isEmpty()) {
            return accounts.get(0);
        } else {
            Account account = new Account(null, accType, accNum, client);
            save(account);
            return account;
        }

    }

    public String getNextAccountNum(String balancePosition) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNativeQuery("""
                                    select substr(?1, 1, 5) || '810' || lpad(cast((cast(substr(acc_num,9,12) as integer) + 1) as varchar), 12 , '0')\s
                                    from account\s
                                    where acc_num like substr(?1, 1, 5) || '%'\s
                                    order by acc_num desc\s
                					fetch first 1 rows  only\s
                """);
        query.setParameter(1, balancePosition);

        List<String> accNums = query.getResultList();

        return accNums.isEmpty() ? balancePosition.substring(0, 5) + "810" + "000000000000" : accNums.get(0);
    }

}
