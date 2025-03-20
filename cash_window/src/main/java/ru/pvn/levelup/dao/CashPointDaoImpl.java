package ru.pvn.levelup.dao;

import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperCashWindow;
import ru.pvn.levelup.entities.CashPoint;

public class CashPointDaoImpl extends AbstractAbsDao<CashPoint> {
    private static CashPointDaoImpl currentDao = new CashPointDaoImpl();

    public CashPointDaoImpl() {
        super(CashPoint.class, DBHelperCashWindow.getEntityManager());
    }

    public static CashPointDaoImpl getDao() {return currentDao;}

}
