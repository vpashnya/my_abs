package ru.pvn.dao.cashwindow;

import ru.pvn.dao.AbstractMyAbsDao;
import ru.pvn.dbhelpers.DBHelperCashWindow;
import ru.pvn.objclasses.cashwindow.CashDocumentInternal;

public class CashDocumentInternalDaoImpl extends AbstractMyAbsDao<CashDocumentInternal> {
    private static CashDocumentInternalDaoImpl currentCao = new CashDocumentInternalDaoImpl();

    private CashDocumentInternalDaoImpl() {
        super(CashDocumentInternal.class, DBHelperCashWindow.getEntityManager());
    }

    public static CashDocumentInternalDaoImpl getDao() {
        return currentCao;
    }

}
