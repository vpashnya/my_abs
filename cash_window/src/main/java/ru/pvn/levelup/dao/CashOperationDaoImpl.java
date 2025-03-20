package ru.pvn.levelup.dao;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperCashWindow;
import ru.pvn.levelup.entities.CashOperation;

import javax.persistence.EntityManager;

public class CashOperationDaoImpl extends AbstractAbsDao<CashOperation> {
    private static CashOperationDaoImpl currentDao = new CashOperationDaoImpl();

    private CashOperationDaoImpl() {
        super(CashOperation.class, DBHelperCashWindow.getEntityManager());
    }

    public static CashOperationDaoImpl getCurrentDao() {
        return currentDao;
    }
}
