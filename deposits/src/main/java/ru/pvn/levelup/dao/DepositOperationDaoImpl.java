package ru.pvn.levelup.dao;

import lombok.Getter;
import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperDeposits;
import ru.pvn.levelup.entities.DepositOperation;

import javax.persistence.EntityManager;

public class DepositOperationDaoImpl extends AbstractAbsDao<DepositOperation> {
    @Getter
    private static DepositOperationDaoImpl currentDao = new DepositOperationDaoImpl();

    private DepositOperationDaoImpl() {
        super(DepositOperation.class, DBHelperDeposits.getEntityManager());
    }

}
