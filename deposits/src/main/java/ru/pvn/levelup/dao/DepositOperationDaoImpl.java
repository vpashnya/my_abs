package ru.pvn.levelup.dao;

import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperDeposits;
import ru.pvn.levelup.entities.DepositOperation;

import javax.persistence.EntityManager;

public class DepositOperationDaoImpl extends AbstractAbsDao<DepositOperation> {
    private static DepositOperationDaoImpl currentDao = new DepositOperationDaoImpl();

    private DepositOperationDaoImpl() {
        super(DepositOperation.class, DBHelperDeposits.getEntityManager());
    }

    public static DepositOperationDaoImpl getCurrentDao() {
        return currentDao;
    }
}
