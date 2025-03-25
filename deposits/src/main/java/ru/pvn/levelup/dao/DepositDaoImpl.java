package ru.pvn.levelup.dao;

import lombok.Getter;
import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperDeposits;
import ru.pvn.levelup.entities.Deposit;

public class DepositDaoImpl extends AbstractAbsDao<Deposit> {
    @Getter
    private static DepositDaoImpl currentDao = new DepositDaoImpl();

    private DepositDaoImpl() {
        super(Deposit.class, DBHelperDeposits.getEntityManager());
    }

}
