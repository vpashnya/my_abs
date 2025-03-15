package ru.pvn.dao.cashwindow;

import ru.pvn.dao.AbstractAbsDao;
import ru.pvn.dbhelpers.DBHelperCashWindow;
import ru.pvn.objclasses.cashwindow.CashDocumentInternal;

public class CashDocumentInternalDaoImpl extends AbstractAbsDao<CashDocumentInternal> {
    private static CashDocumentInternalDaoImpl currentDao = new CashDocumentInternalDaoImpl();

    private CashDocumentInternalDaoImpl() {
        super(CashDocumentInternal.class, DBHelperCashWindow.getEntityManager());
    }

    public static CashDocumentInternalDaoImpl getDao() {
        return currentDao;
    }

}
