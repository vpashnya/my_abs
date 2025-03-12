package ru.pvn.dao.fincore;

import ru.pvn.dao.AbstractMyAbsDao;
import ru.pvn.dbhelpers.DBHelperFinCore;
import ru.pvn.objclasses.fincore.CurrentBankInfo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CurrentBankInfoDaoImpl extends AbstractMyAbsDao<CurrentBankInfo> {
    private static CurrentBankInfoDaoImpl currentDao = new CurrentBankInfoDaoImpl();

    private CurrentBankInfoDaoImpl() {
        super(CurrentBankInfo.class, DBHelperFinCore.getEntityManager());
    }

    public static CurrentBankInfoDaoImpl getDao() {
        return currentDao;
    }

    @Override
    public void delete(int id) {
        throw new RuntimeException("Нельзя удалить информацию о Банке");
    }

    @Override
    public void save(CurrentBankInfo object) {
        throw new RuntimeException("Нельзя изменить информацию о Банке");
    }

    @Override
    public CurrentBankInfo get(int id) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("from CurrentBankInfo", CurrentBankInfo.class);
        List<CurrentBankInfo> list = query.getResultList();
        return list.getFirst();
    }

    public void switch2NextDay(CurrentBankInfo currentBankInfo) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        currentBankInfo.setOperDay(currentBankInfo.getOperDay().plusDays(1));
        entityManager.merge(currentBankInfo);
        entityManager.getTransaction().commit();
    }

}
