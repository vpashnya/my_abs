package ru.pvn.levelup.dao;

import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperDeposits;
import ru.pvn.levelup.entities.Deposit;

public class DepositDaoImpl extends AbstractAbsDao<Deposit> {
    private static DepositDaoImpl currentDao = new DepositDaoImpl();

    private DepositDaoImpl() {
        super(Deposit.class, DBHelperDeposits.getEntityManager());
    }

    public static DepositDaoImpl getCurrentDao() {
        return currentDao;
    }
}
