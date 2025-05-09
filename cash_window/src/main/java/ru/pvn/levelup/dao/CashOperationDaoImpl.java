package ru.pvn.levelup.dao;

import lombok.Getter;
import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperCashWindow;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.entities.CashPoint;

import javax.persistence.Query;

public class CashOperationDaoImpl extends AbstractAbsDao<CashOperation> {
    @Getter
    private static CashOperationDaoImpl currentDao = new CashOperationDaoImpl();

    private CashOperationDaoImpl() {
        super(CashOperation.class, DBHelperCashWindow.getEntityManager());
    }

    public Integer getCountOperationsByCashPoint(CashPoint cashPoint) {
        Query query = getEntityManager().createQuery("select count(c) from CashOperation c where c.cashPoint = :cashPoint");
        query.setParameter("cashPoint", cashPoint);
        query.setMaxResults(1);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
}
